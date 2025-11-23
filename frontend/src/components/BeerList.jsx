import React, { useEffect, useState } from 'react'
import api from '../api'
import BeerItem from './BeerItem'

export default function BeerList({ search = '' }){
  const [beers, setBeers] = useState([])
  const [loading, setLoading] = useState(true)
  const [page, setPage] = useState(1)
  const pageSize = 6

  useEffect(()=>{
    fetchBeers()
  }, [])

  useEffect(()=>{
    setPage(1)
  }, [search])

  async function fetchBeers(){
    try{
      setLoading(true)
      const res = await api.get('/v1/beers')
      setBeers(res.data)
    }catch(err){
      console.error(err)
      alert('Erro ao buscar cervejas')
    }finally{
      setLoading(false)
    }
  }

  if(loading) return <p>Carregando...</p>

  const effective = beers.filter(b => {
    if(!search || search.trim() === '') return true
    const s = search.toLowerCase()
    return (b.name || '').toLowerCase().includes(s) || (b.brand || '').toLowerCase().includes(s)
  })

  const total = effective.length
  const totalPages = Math.max(1, Math.ceil(total / pageSize))
  const start = (page - 1) * pageSize
  const pageItems = effective.slice(start, start + pageSize)

  return (
    <div>
      <div className="list-header">
        <h2 style={{margin:0}}>Lista de Cervejas</h2>
        <div className="site-sub">Total: {total}</div>
      </div>
      {total === 0 && <p>Nenhuma cerveja cadastrada.</p>}
      <div className="cards">
        {pageItems.map(b => (
          <BeerItem key={b.id} beer={b} onChange={fetchBeers} />
        ))}
      </div>

      <div className="pagination">
        <button onClick={()=>setPage(1)} disabled={page===1}>◀◀</button>
        <button onClick={()=>setPage(p=>Math.max(1,p-1))} disabled={page===1}>◀</button>
        <span> Página {page} / {totalPages} </span>
        <button onClick={()=>setPage(p=>Math.min(totalPages,p+1))} disabled={page===totalPages}>▶</button>
        <button onClick={()=>setPage(totalPages)} disabled={page===totalPages}>▶▶</button>
      </div>
    </div>
  )
}
