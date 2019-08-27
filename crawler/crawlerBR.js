const puppeteer = require('puppeteer')
const rp = require('request-promise')
const cheerio = require('cheerio')
const url = 'https://cn.br-petrobras.com.br/portal/buscadepostos/index.jsf'

var $
var page
let timeoutOption = { timeout: 60000 }
    (async () => {
        const browser = await puppeteer.launch({ headless: false })
        page = await browser.newPage()
        await page.goto(url)
        await page.waitFor('#selectEstado', timeoutOption)
        console.log('seletor cidade encontrado')
        await page.waitFor('#selectCidade', timeoutOption)
        console.log('seletor cidade encontrado')
        await page.screenshot({ path: 'example.png' })
        await page.evaluate(() => window.stop())
        $ = cheerio.load(await page.content())
        console.log($('head > title').text())
        var states = getOptionsOfSelect($('#selectEstado'))
        states.forEach((value, index) => {
            selectState(value)
            $ = cheerio.load(await page.content())
            //cache city results
            var cities = getOptionsOfSelect($('select#selectCidade'))
            selectCity(cities[0])
            var options = {
                method: 'POST',
                mode: 'no-cors',
                form: {
                    selectEstado: 'AP',
                    autoScroll: null,
                    selectCidade: 'FERREIRA+GOMES',
                    selectBairro: '0',
                    'busca-postos_SUBMIT': '1'
                },
                headers: {
                    "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:68.0) Gecko/20100101 Firefox/68.0",
                    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
                    "Accept-Language": "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3",
                    "Content-Type": "application/x-www-form-urlencoded",
                    "Upgrade-Insecure-Requests": "1"
                },
                uri: 'https://cn.br-petrobras.com.br/portal/buscadepostos/pages/ConsultaPostos.jsf',
                transform: function (body) {
                    return cheerio.load(body);
                }
            }
        })

    })()

async function selectState(state) {
    await page.select('select#selectEstado', state)
    await page.waitFor('#selectCidade', timeoutOption)
    await page.evaluate(() => window.stop())
    console.log('seletor cidade encontrado')
}

async function selectCity(city) {
    await page.select('select#selectCidade', city)
    await page.waitFor('#selectBairro', timeoutOption)
    await page.evaluate(() => window.stop())
    console.log('seletor cidade encontrado')
}

function getOptionsOfSelect(select) {
    var elements = []
    //cache city results
    select.find('option').each((i, op) => {
        let val = $(op).text()
        if (val !== 'Selecione') elements.push(val)
    })
    return elements
}

(async () => {
    let body = await rp(options)
    console.log(body('h3').text())
    // $ = cheerio.load(await page.content())
    //         //cache city results
    //         var cities = getOptionsOfSelect($('select#selectCidade'))
    // selectCity(cities[0])
})()