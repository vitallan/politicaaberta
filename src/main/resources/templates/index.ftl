
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" xmlns:th="http://www.w3.org/1999/xhtml"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Política Aberta</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Dados abertos da Política Brasileira" />
	<meta name="keywords" content="politica, deputado, cota parlamentar, custo, partido, camara dos deputados" />
	<meta name="author" content="Allan Vital" />

  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="/static/images/favicon.ico">

	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700,900' rel='stylesheet' type='text/css'>

	<link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700" rel="stylesheet">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="/static/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="/static/css/icomoon.css">
	<!-- Simple Line Icons -->
	<link rel="stylesheet" href="/static/css/simple-line-icons.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="/static/css/bootstrap.css">
	<!-- Theme style  -->
	<link rel="stylesheet" href="/static/css/style.css">

	<!-- Modernizr JS -->
	<script src="/static/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="/static/js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
	
	
	<div id="fh5co-page">
	<header id="fh5co-header" role="banner">
		<div class="container">
			<div class="header-inner">
				<h1><i class="sl-icon-briefcase"></i><a href="/">Política Aberta</a></h1>
				<nav role="navigation">
					<ul>
						<li><a class="active" href="/">Inicio</a></li>
						<li><a href="/deputados">Deputados</a></li>
						<li><a href="/partidos">Partidos</a></li>
						<li><a href="/faq">Perguntas Frequentes</a></li>
						<li><a href="/contato">Contato</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
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
                            <a href="#" class="list-group-item"><strong>R$ ${expense.value}</strong> - ${expense.deputyName}</a>
					    </#list>
					</div>
				</div>
				<div class="col-md-6 text-center">
					<h3>Quais foram as despesas mais caras em ${month}/${year}</h3>
					<div class="list-group">
						<#list biggestExpenses as expense>
                            <a href="#" class="list-group-item"><strong>R$ ${expense.value}</strong> - ${expense.deputyName} em <strong>${expense.receiver}</strong></a>
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
	
	<footer id="fh5co-footer" role="contentinfo">
	
		<div class="container">
			<div class="col-md-6 col-sm-12 col-sm-push-0 col-xs-12 col-xs-push-0">
				<h3>Sobre</h3>
				<p>Dados abertos da Política brasileira</p>
			</div>
			<div class="col-md-6 col-md-push-1 col-sm-12 col-sm-push-0 col-xs-12 col-xs-push-0">
				<h3>Menu</h3>
				<ul class="float">
					<li><a class="active" href="/">Inicio</a></li>
					<li><a href="/deputados">Deputados</a></li>
					<li><a href="/partidos">Partidos</a></li>

				</ul>
				<ul class="float">
					<li><a href="/faq">Perguntas Frequentes</a></li>
					<li><a href="/sobre">Sobre</a></li>
					<li><a href="/contato">Contato</a></li>
				</ul>

			</div>
			
			
			<div class="col-md-12 fh5co-copyright text-center">
				<p>&copy; 2017 Política Aberta. Todos os direitos reservados. <span>Desenvolvido e idealizado por <a target="_blank" href="http://allanvital.com">Allan Vital</a></span></p>
			</div>
			
		</div>
	</footer>
	</div>
	
	
	<!-- jQuery -->
	<script src="/static/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="/static/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="/static/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="/static/js/jquery.waypoints.min.js"></script>
	<!-- MAIN JS -->
	<script src="/static/js/main.js"></script>

	</body>
</html>
