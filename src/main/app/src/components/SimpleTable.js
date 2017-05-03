import React, { Component } from 'react';
import { Table } from 'react-materialize';
import $ from 'jquery';

export default class SimpleTable extends Component {

	constructor() {
		super();
		this.state = {lista : []};
	}

	componentDidMount() {
		$.ajax({
			url:"/api/summarized" + this.props.path,
			dataType: 'json',
			success:function(resposta){    
				this.setState({lista:resposta});
			}.bind(this)
		});  
	}

	render() {
		return (
		<div>
			<h5>{this.props.title}</h5>
			<Table>
			<thead>
				<tr>
					<td>Nome:</td>
					<td>Valor:</td>
				</tr>
			</thead>
			<tbody>
				{
					this.state.lista.map(function(item) {
						return (
							<tr key={item.id}>
								<td>{item.description}</td>
								<td>R$ {item.quantity}</td>
							</tr>
						);
					})

				}
			</tbody>
			</Table>
		</div>
		);
	}

}