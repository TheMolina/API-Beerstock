import React, { useState } from 'react'
import BeerList from './components/BeerList'
import BeerForm from './components/BeerForm'

export default function App(){
  const [refreshKey, setRefreshKey] = useState(0)

  function refresh(){
    setRefreshKey(k => k + 1)
  }

  return (
    <div className="container">
      <h1>Beerstock</h1>
      <BeerForm onCreated={refresh} />
      <BeerList key={refreshKey} />
    </div>
  )
}
