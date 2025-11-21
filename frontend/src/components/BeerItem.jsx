import React, { useState } from 'react'
import api from '../api'

export default function BeerItem({ beer, onChange }){
  const [loading, setLoading] = useState(false)
  const [editing, setEditing] = useState(false)
  const [form, setForm] = useState({
    name: beer.name || '',
    brand: beer.brand || '',
    max: beer.max || 0,
    quantity: beer.quantity || 0,
    price: beer.price || 0
  })

  async function remove(){
    if(!confirm('Excluir cerveja?')) return
    try{
      setLoading(true)
      await api.delete(`/v1/beers/${beer.id}`)
      onChange()
    }catch(err){
      alert(err?.response?.data?.message || 'Erro ao excluir')
    }finally{setLoading(false)}
  }

  async function changeQuantity(amount){
    try{
      setLoading(true)
      await api.patch(`/v1/beers/${beer.id}/${amount>0?'increment':'decrement'}`, { quantity: Math.abs(amount) })
      onChange()
    }catch(err){
      alert(err?.response?.data?.message || 'Erro na operação de estoque')
    }finally{setLoading(false)}
  }

  async function changePrice(){
    const value = prompt('Novo preço (ex: 12.5)', beer.price)
    if(value == null) return
    const price = parseFloat(value)
    if(Number.isNaN(price)) return alert('Preço inválido')
    try{
      setLoading(true)
      await api.patch(`/v1/beers/${beer.id}/price`, { price })
      onChange()
    }catch(err){
      alert(err?.response?.data?.message || 'Erro ao atualizar preço')
    }finally{setLoading(false)}
  }

  function startEdit(){
    setForm({ name: beer.name, brand: beer.brand, max: beer.max, quantity: beer.quantity, price: beer.price })
    setEditing(true)
  }

  function changeField(e){
    const { name, value } = e.target
    setForm(prev => ({...prev, [name]: value}))
  }

  async function saveEdit(){
    try{
      setLoading(true)
      await api.put(`/v1/beers/${beer.id}`, {
        name: form.name,
        brand: form.brand,
        max: parseInt(form.max),
        quantity: parseInt(form.quantity),
        price: parseFloat(form.price)
      })
      setEditing(false)
      onChange()
    }catch(err){
      alert(err?.response?.data?.message || 'Erro ao salvar')
    }finally{setLoading(false)}
  }

  return (
    <li>
      {!editing ? (
        <>
          <strong>{beer.name}</strong> — {beer.brand} — Qtd: {beer.quantity}/{beer.max} — R$ {beer.price}
          <div style={{display:'inline-block', marginLeft:12}}>
            <button onClick={()=>changeQuantity(1)} disabled={loading}>+1</button>
            <button onClick={()=>changeQuantity(-1)} disabled={loading}>-1</button>
            <button onClick={changePrice} disabled={loading}>Alterar preço</button>
            <button onClick={startEdit} disabled={loading}>Editar</button>
            <button onClick={remove} disabled={loading}>Excluir</button>
          </div>
        </>
      ) : (
        <div className="edit-row">
          <input name="name" value={form.name} onChange={changeField} />
          <input name="brand" value={form.brand} onChange={changeField} />
          <input name="max" type="number" value={form.max} onChange={changeField} />
          <input name="quantity" type="number" value={form.quantity} onChange={changeField} />
          <input name="price" type="number" step="0.01" value={form.price} onChange={changeField} />
          <button onClick={saveEdit} disabled={loading}>Salvar</button>
          <button onClick={()=>setEditing(false)} disabled={loading}>Cancelar</button>
        </div>
      )}
    </li>
  )
}
