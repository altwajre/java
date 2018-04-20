import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';

class LoggingButton extends React.Component {
  // This syntax ensures 'this' is bound within handleClick.
  // Warning: this is *experimental* syntax
  handleClick = () => {
    console.log('this is:', this);

    // fetch("http://localhost:8080/api/whiskies", { mode: 'no-cors' })
    //   .then(response => console.log(response))

    fetch("http://www.google.com", {
      // method: 'GET',
      //  mode: 'no-cors' 
      })
      .then(response => console.log(response))

    }
  render() {
    return (
      <button onClick={this.handleClick}>
        Click me
      </button>
    )
  }
}

ReactDOM.render(<LoggingButton />, document.getElementById('root'));
registerServiceWorker();
