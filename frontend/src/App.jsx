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
    <div className="app-wrap">
      <div className="container">
        <header className="site-header">
          <div>
            <div className="site-title">Beerstock</div>
            <div className="site-sub">Gerencie seu estoque de cervejas</div>
          </div>
          <div className="header-actions">
            <div className="search">
              <input placeholder="Buscar por nome ou marca" value={search} onChange={e=>setSearch(e.target.value)} />
            </div>
            <button className="btn" onClick={()=>window.scrollTo({top:600,behavior:'smooth'})}>Ver Cervejas</button>
          </div>
        </header>

        <div className="hero">
          <div className="hero-left">
            <h2 style={{margin:0}}>Encontre a cerveja perfeita</h2>
            <p className="site-sub">Cadastre, atualize e controle o estoque com facilidade.</p>
          </div>
          <div className="hero-right">
            {/* reserved for illustration */}
          </div>
        </div>

        <div className="grid">
          <div>
            <BeerList key={refreshKey} search={search} />
          </div>
          <aside>
            <div className="panel">
              <BeerForm onCreated={refresh} />
            </div>
          </aside>
        </div>
      </div>
    </div>
  )
}
