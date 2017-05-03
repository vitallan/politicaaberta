import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css';
var ReactGA = require('react-ga');

ReactGA.initialize('UA-70145133-2');

//function logPageView() {
  ReactGA.set({ page: window.location.pathname });
  ReactGA.pageview(window.location.pathname);
//}
//var app = document.getElementById('app');
//ReactDOM.render(<Router routes={routes} onUpdate={logPageView} />, app);

ReactDOM.render(
  <App />,
  document.getElementById('root')
);
