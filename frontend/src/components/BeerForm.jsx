import React, { useState } from 'react'
import axios from 'axios'

export default function BeerForm(){
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
      await axios.post('/api/v1/beers', {
        name: form.name,
        brand: form.brand,
        max: parseInt(form.max),
        quantity: parseInt(form.quantity),
        price: parseFloat(form.price)
      })
      alert('Cerveja criada')
      setForm({name:'', brand:'', max:10, quantity:0, price:0.0})
      window.location.reload()
    }catch(err){
      console.error(err)
      alert(err?.response?.data?.message || 'Erro ao criar')
    }finally{
      setSaving(false)
    }
  }

  return (
    <form onSubmit={submit} className="beer-form">
      <h2>Cadastrar Cerveja</h2>
      <div>
        <label>Nome</label>
        <input name="name" value={form.name} onChange={change} required />
      </div>
      <div>
        <label>Marca</label>
        <input name="brand" value={form.brand} onChange={change} required />
      </div>
      <div>
        <label>Estoque Máx</label>
        <input name="max" type="number" value={form.max} onChange={change} required />
      </div>
      <div>
        <label>Quantidade</label>
        <input name="quantity" type="number" value={form.quantity} onChange={change} required />
      </div>
      <div>
        <label>Preço</label>
        <input name="price" type="number" step="0.01" value={form.price} onChange={change} required />
      </div>
      <button type="submit" disabled={saving}>{saving? 'Salvando...' : 'Salvar'}</button>
    </form>
  )
}
