var request = require('supertest');
//var knex = require('../server/db');

process.env.NODE_ENV = 'test';

describe('Testando endpoints de deputado', function () {
  var server;

  after(function(done){
    process.exit(0);
  });

  beforeEach(function() {
    server = require('../app').listen(3000);
  });

  afterEach(function(done) {
    server.close(done);
  });

  it('Responde o post de um deputado completo com sucesso ', function(done) {
    var deputy = {name:"Meu Nome", uf:"SP", site_id:1, secondary_site_id:2, party: "PT"};
    request(server)
      .post('/api/deputy')
      .send(deputy)
      .expect(200, done);
  });

  it('Responde o post de um deputado com UF errado com bad request ', function(done) {
    var deputy = {name:"Meu Nome", uf:"SPA", site_id:1, secondary_site_id:2, party: "PT"};
    request(server)
      .post('/api/deputy')
      .send(deputy)
      .expect(400, done);
  });

  it('Responde o post de um deputado incompleto com bad request ', function(done) {
    var deputy = {naonome:"Nao deve funcionar"};
    request(server)
      .post('/api/deputy')
      .send(deputy)
      .expect(400, done);
  });

  it('Responde o post de uma expense completa com sucesso ', function(done) {
    var expense = {description: "COMPRA", url: "http://url.com", deputy_site_id: 1, value: 15.50, date: 1507424588892};
    request(server)
      .post('/api/expense')
      .send(expense)
      .expect(200, done);
  });

  it('Responde o post de uma expense incompleta com bad request ', function(done) {
    var expense = {description: "COMPRA"};
    request(server)
      .post('/api/expense')
      .send(expense)
      .expect(400, done);
  });

});
