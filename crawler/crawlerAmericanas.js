let request = require('request')
let cheerio = require('cheerio')
let URL = require('url-parse')
let fetch = require("node-fetch")

let csvWritter = require('./saveCSV')

let url = "http://lmp.todomundovai.com.br/"
console.log("Visiting page " + url);
request(url, function (error, response, body) {
    if (error) {
        console.log("Error: " + error);
    }
    // Check status code (200 is HTTP OK)
    console.log("Status code: " + response.statusCode);
    if (response.statusCode === 200) {
        // Parse the document body
        var $ = cheerio.load(body);
        let scripts = $('html > body > script').filter(item => !item).html().trim().split('=')
        let stringObject = scripts[scripts.findIndex(item => item.includes('window.stores')) + 1].replace(/;/g, '')
        let stores = JSON.parse(stringObject).map(item => {
            var splittedAddress = item.address.split(' ')
            let cityName = item.city !== undefined ? item.city.name : ''
            //TODO to improve data collected, it would be better to do a rerverse geocode based on lat/lon, and them get the missing information from there.
            return {
                prefix: splittedAddress.shift(),
                address: splittedAddress.join(' '),
                number: item.number,
                complement: '',
                neighborhood: '',
                city: cityName,
                state: item.state_id,
                country: '',
                zipcode: item.cep,
                lat: item.latitude,
                lon: item.longitude,
            }
        })
        csvWritter.saveCSV('Americanas', stores)
    }
})