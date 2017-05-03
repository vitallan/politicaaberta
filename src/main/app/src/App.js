import React, { Component } from 'react';
import './App.css';
import { Navbar, Footer, Col } from 'react-materialize';
import SimpleTable from './components/SimpleTable';

class App extends Component {
  render() {
    return (
      <div>
        <Navbar className="deep-purple darken-2" brand="Politica Aberta" right>
          
        </Navbar> 
        <div className="container">
          <div className="row">
            <Col s={6}>
              <SimpleTable title="Deputados que mais gastaram no mês passado" path="/expensive-deputies" />
            </Col>
            <Col s={6}>
              <SimpleTable title="Partidos com maior custo médio por deputado " path="/expensive-average-deputy" />
            </Col>
          </div>
          <Footer className="deep-purple darken-4" copyrights="&copy; 2017 Allan Vital"
            links={
              <ul>
                <li><a className="grey-text text-lighten-3" href="http://allanvital.com">allanvital.com</a></li>
              </ul>
            } >

              <h5 className="white-text">Politica Aberta</h5>
              <p className="grey-text text-lighten-4">Idealizado e desenvolvido por Allan Vital.</p>
          </Footer>
        </div>
      </div>
    );
  }
}

export default App;
