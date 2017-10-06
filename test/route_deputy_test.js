var request = require('supertest');
var util    = require('util');

describe('Testando endpoints de deputado', function () {
  var server;

  beforeEach(function() {
    server = require('../app').listen(3000);
  });

  afterEach(function(done) {
    server.close(done);
  });

  after(function(done){
    process.exit(0); //TODO: check this
  });

  it('Responde o post de um deputado completo com sucesso ', function(done) {
    var deputy = {name:"Meu Nome", uf:"SP", site_id:"1", secondary_site_id:"2"}
    request(server)
      .post('/api/deputy')
      .send(deputy)
      .expect(200, done);
  });

  it('Responde o post de um deputado incompleto com bad request ', function(done) {
    var deputy = {naonome:"Nao deve funcionar"}
    request(server)
      .post('/api/deputy')
      .send(deputy)
      .expect(400, done);
  });

});
