<#include "include/header.ftl">
	<div id="fh5co-intro-section">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">
					<h2>Dados Abertos da Política Brasileira</h2>
				</div>
			</div>
		</div>
	</div>
	<div id="fh5co-featured-section">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center">
					<h3>Quem mais usou a cota parlamentar em ${month}/${year}</h3>
					<div class="list-group">
					    <#list monthlyExpenses as expense>
                            <a href="/deputado/${expense.deputyNormalizedName}" class="list-group-item"><strong>R$ ${expense.value}</strong> - ${expense.deputyName}</a>
					    </#list>
					</div>
				</div>
				<div class="col-md-6 text-center">
					<h3>Quais foram as despesas mais caras em ${month}/${year}</h3>
					<div class="list-group">
						<#list biggestExpenses as expense>
                            <a href="/deputado/${expense.deputyNormalizedName}" class="list-group-item"><strong>R$ ${expense.value}</strong> - ${expense.deputyName} em <strong>${expense.receiver}</strong></a>
                        </#list>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="fh5co-services-section">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3 text-center fh5co-heading">
					<h2>Objetivo</h2>
					<p>Os gastos públicos são abertos, porém, nem sempre é facil ou simples acompanhar como anda o seu deputado. O Política Aberta tenta auxiliar você, mostrando as principais informações dos políticos de maneira fácil de ser consultada e analisada.</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 services-inner text-center">
					<span><i class="sl-icon-speedometer"></i></span>
					<h3>Cota Parlamentar</h3>
					<p>Além do salário, seu deputado federal tem direito a reembolsos via cota parlamentar. Onde ele usa esse beneficio?</p>
				</div>
				<div class="col-md-4 services-inner text-center">
					<span><i class="sl-icon-book-open"></i></span>
					<h3>Partidos</h3>
					<p>Quanto custa um deputado do seu partido por mês? Qual é o partido com maior número de deputados na Camara Federal?</p>
				</div>
				<div class="col-md-4 services-inner text-center">
					<span><i class="sl-icon-layers"></i></span>
					<h3>Proposições</h3>
					<p>Seu deputado participa ativamente das proposições? Bancadas? Qual tem sido o real papel dele no governo?</p>
				</div>

			</div>
		</div>
	</div>
<#include "include/footer.ftl">
