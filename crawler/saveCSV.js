const createCsvWriter = require('csv-writer').createObjectCsvWriter;

exports.saveCSV = function (fileName, objectToSave) {

    let csvWriter = createCsvWriter({
        path: fileName + '.csv',
        header: [
          {id: 'prefix', title: 'Logradouro'},
          {id: 'address', title: 'Endereco'},
          {id: 'number', title: 'Numero'},
          {id: 'complement', title: 'Complemento'},
          {id: 'neighborhood', title: 'Bairro'},
          {id: 'city', title: 'Cidade'},
          {id: 'state', title: 'Estado'},
          {id: 'country', title: 'Pais'},
          {id: 'zipcode', title: 'Cep'},
          {id: 'lat', title: 'Latitude'},
          {id: 'lon', title: 'Longitude'},
        ]
    });
    
    csvWriter
        .writeRecords(objectToSave)
        .then(()=> console.log('The CSV file was written successfully on ' + fileName));
}