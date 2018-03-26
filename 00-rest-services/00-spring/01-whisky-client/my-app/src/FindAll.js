import React, { Component } from 'react';
import CreateButton from './CreateButton';

var rest, mime, errorCode, client;
rest = require('rest'),
  mime = require('rest/interceptor/mime');
errorCode = require('rest/interceptor/errorCode');

client = rest.wrap(mime).wrap(errorCode);

class FindAll extends Component {

  constructor(props) {
    super(props);
    this.state = {whiskies: []};
  }
  componentDidMount() {
    client({ path: 'http://localhost:8080/whiskies' })
      .then(
        response => {
          console.log(response);
          console.log(response.entity);
          this.setState( {whiskies: response.entity} );
        }
      );
  }

  render() {
    return (
      <div>
        <WhiskyListComponent whiskies={this.state.whiskies}/>
        <br/>
        <CreateButton changeOperation={this.props.changeOperation} />
      </div>
    );
  }
}

export class WhiskyListComponent extends React.Component{
  render() {
    var whiskies = this.props.whiskies.map(whisky =>
      <WhiskyComponent key={whisky.id} whisky={whisky}/>
    );
    return (
      <table>
        <tbody>
        <tr>
          <th>Name</th>
          <th>Origin</th>
        </tr>
        {whiskies}
        </tbody>
      </table>
    )
  }
}

export class WhiskyComponent extends React.Component{
  render() {
    return (
      <tr>
        <td>{this.props.whisky.Name}</td>
        <td>{this.props.whisky.Origin}</td>
      </tr>
    )
  }
}

export default FindAll;
