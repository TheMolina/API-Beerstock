import React, { useState } from 'react'
import api from '../api'

export default function BeerForm({ onCreated }){
  const [form, setForm] = useState({name:'', brand:'', max:10, quantity:0, price:0.0})
  const [saving, setSaving] = useState(false)

  function change(e){
    const { name, value } = e.target
    setForm(prev => ({...prev, [name]: value}))
  }

  async function submit(e){
    e.preventDefault()
    try{
      setSaving(true)
      await api.post('/v1/beers', {
        name: form.name,
        brand: form.brand,
        max: parseInt(form.max),
        quantity: parseInt(form.quantity),
        price: parseFloat(form.price)
      })
      alert('Cerveja criada')
      setForm({name:'', brand:'', max:10, quantity:0, price:0.0})
      if(typeof onCreated === 'function') onCreated()
    }catch(err){
      console.error(err)
      alert(err?.response?.data?.message || 'Erro ao criar')
    }finally{
      setSaving(false)
    }
  }

  return (
    <form onSubmit={submit} className="beer-form">
      <h3 style={{marginTop:0}}>Cadastrar Cerveja</h3>
      <div>
        <label>Nome</label>
        <input name="name" value={form.name} onChange={change} required />
      </div>
      <div>
        <label>Marca</label>
        <input name="brand" value={form.brand} onChange={change} required />
      </div>
      <div style={{display:'flex',gap:8}}>
        <div style={{flex:1}}>
          <label>Estoque Máx</label>
          <input name="max" type="number" value={form.max} onChange={change} required />
        </div>
        <div style={{width:120}}>
          <label>Quantidade</label>
          <input name="quantity" type="number" value={form.quantity} onChange={change} required />
        </div>
      </div>
      <div>
        <label>Preço</label>
        <input name="price" type="number" step="0.01" value={form.price} onChange={change} required />
      </div>
      <div style={{marginTop:12}}>
        <button type="submit" className="btn" disabled={saving}>{saving? 'Salvando...' : 'Salvar'}</button>
      </div>
    </form>
  )
}
