import React, { useState } from 'react'
import BeerList from './components/BeerList'
import BeerForm from './components/BeerForm'

export default function App(){
  const [refreshKey, setRefreshKey] = useState(0)
  const [search, setSearch] = useState('')

  function refresh(){
    setRefreshKey(k => k + 1)
  }

  return (
    <div className="container">
      <h1>Beerstock</h1>
      <div style={{display:'flex', gap:12, alignItems:'center'}}>
        <BeerForm onCreated={refresh} />
        <div style={{marginLeft: 'auto'}}>
          <input placeholder="Buscar por nome ou marca" value={search} onChange={e=>setSearch(e.target.value)} />
        </div>
      </div>
      <BeerList key={refreshKey} search={search} />
    </div>
  )
}
