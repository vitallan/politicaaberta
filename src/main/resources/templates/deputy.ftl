<#include "include/header.ftl">
<div id="fh5co-featured-section">
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2 text-center pt-6">
            <h2>${deputy.deputyName} - ${deputy.partyName}</h2>
        </div>
        <div class="col-md-12 text-center">
            <h3>Despesas dos últimos três meses</h3>
            <div id="MainMenu">
                <div class="list-group panel">
                    <a href="#demo3" class="row list-group-item list-group-item-success strong" data-toggle="collapse" data-parent="#MainMenu">Total de despesas no mês passado R$ ${deputy.lastMonthSummarized}<i class="fa fa-caret-down"></i></a>
                    <div class="collapse" id="demo3">
                        <#list deputy.lastMonth as expense>
                            <div class="list-group-item row">
                                <div class="col-sm-4">${expense.description}</div>
                                <div class="col-sm-4">${expense.receiver}</div>
                                <div class="col-sm-4">R$ ${expense.value}</div>
                            </div>
                        </#list>
                    </div>
                    <a href="#demo4" class="row list-group-item list-group-item-success strong" data-toggle="collapse" data-parent="#MainMenu">Total de despesas a dois meses atrás R$ ${deputy.twoMonthsAgoSummarized}<i class="fa fa-caret-down"></i></a>
                    <div class="collapse" id="demo4">
                        <#list deputy.twoMonthsAgo as expense>
                        <div class="list-group-item row">
                            <div class="col-sm-4">${expense.description}</div>
                            <div class="col-sm-4">${expense.receiver}</div>
                            <div class="col-sm-4">R$ ${expense.value}</div>
                        </div>
                    </#list>
                    </div>
                    <a href="#demo5" class="row list-group-item list-group-item-success strong" data-toggle="collapse" data-parent="#MainMenu">Total de despesas a três meses atrás R$ ${deputy.threeMonthsAgoSummarized}<i class="fa fa-caret-down"></i></a>
                    <div class="collapse" id="demo5">
                        <#list deputy.threeMonthsAgo as expense>
                        <div class="list-group-item row">
                            <div class="col-sm-4">${expense.description}</div>
                            <div class="col-sm-4">${expense.receiver}</div>
                            <div class="col-sm-4">R$ ${expense.value}</div>
                        </div>
                    </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<#include "include/footer.ftl">