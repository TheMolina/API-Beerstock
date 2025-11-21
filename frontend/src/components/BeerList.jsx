import React, { useEffect, useState } from 'react'
import axios from 'axios'

export default function BeerList(){
  const [beers, setBeers] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(()=>{
    fetchBeers()
  }, [])

  async function fetchBeers(){
    try{
      setLoading(true)
      const res = await axios.get('/api/v1/beers')
      setBeers(res.data)
    }catch(err){
      console.error(err)
      alert('Erro ao buscar cervejas')
    }finally{
      setLoading(false)
    }
  }

  if(loading) return <p>Carregando...</p>

  return (
    <div>
      <h2>Lista de Cervejas</h2>
      {beers.length === 0 && <p>Nenhuma cerveja cadastrada.</p>}
      <ul>
        {beers.map(b => (
          <li key={b.id}>{b.name} — {b.brand} — Qtd: {b.quantity} / Max: {b.max} — R$ {b.price}</li>
        ))}
      </ul>
    </div>
  )
}
