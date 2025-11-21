const { test, expect } = require('@playwright/test');

test.describe('Beerstock E2E', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('http://localhost:3001')
  })

  test('lista inicial e criação de cerveja', async ({ page }) => {
    // verifica se a página carrega
    await expect(page.locator('h1')).toHaveText('Beerstock')

    // contar itens existentes
    const initial = await page.locator('ul > li').count()

    // preencher formulário
    await page.fill('input[name="name"]', 'E2E Beer')
    await page.fill('input[name="brand"]', 'Playwright')
    await page.fill('input[name="max"]', '20')
    await page.fill('input[name="quantity"]', '5')
    await page.fill('input[name="price"]', '9.5')
    await page.click('button:has-text("Salvar")')

    // aguardar notificação e novo item na lista
    await page.waitForTimeout(500)
    const after = await page.locator('ul > li').count()
    expect(after).toBeGreaterThanOrEqual(initial + 1)
    await expect(page.locator('li', { hasText: 'E2E Beer' })).toBeTruthy()
  })

  test('editar e excluir cerveja', async ({ page }) => {
    // localizar item criado
    const item = page.locator('li', { hasText: 'E2E Beer' })
    await expect(item).toBeVisible()

    // clicar editar
    await item.locator('button:has-text("Editar")').click()
    // alterar nome
    await item.locator('input[name="name"]').fill('E2E Beer Updated')
    await item.locator('button:has-text("Salvar")').click()

    await page.waitForTimeout(500)
    await expect(page.locator('li', { hasText: 'E2E Beer Updated' })).toBeVisible()

    // excluir
    await page.locator('li', { hasText: 'E2E Beer Updated' }).locator('button:has-text("Excluir")').click()
    // confirmar prompt (Playwright auto-dismisses dialogs by default; accept manually)
    page.on('dialog', async dialog => { await dialog.accept() })
    await page.waitForTimeout(500)

    // checar que não existe mais
    const exists = await page.locator('li', { hasText: 'E2E Beer Updated' }).count()
    expect(exists).toBe(0)
  })
})
