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
                    <a href="#demo3" class="list-group-item list-group-item-success strong" data-toggle="collapse" data-parent="#MainMenu">Total de despesas no mês passado R$ ${deputy.lastMonthSummarized}<i class="fa fa-caret-down"></i></a>
                    <div class="collapse" id="demo3">
                        <#list deputy.lastMonth as expense>
                            <div class="list-group-item">
                                a
                                <div class="col-sm-2">${expense.description}</div>
                                <div class="col-sm-2">${expense.receiver}</div>
                            </div>
                        </#list>
                    </div>
                    <a href="#demo4" class="list-group-item list-group-item-success strong" data-toggle="collapse" data-parent="#MainMenu">Total de despesas a dois meses atrás R$ ${deputy.twoMonthsAgoSummarized}<i class="fa fa-caret-down"></i></a>
                    <div class="collapse" id="demo4">
                        <a href="#" class="list-group-item">Subitem 1</a>
                        <a href="#SubSubMenu4" class="list-group-item strong" data-toggle="collapse" data-parent="#SubSubMenu4"><i class="glyphicon glyphicon-thumbs-up"></i> Subitem 2 <i class="fa fa-caret-down"></i></a>
                    </div>
                    <a href="#demo5" class="list-group-item list-group-item-success strong" data-toggle="collapse" data-parent="#MainMenu">Total de despesas a três meses atrás R$ ${deputy.threeMonthsAgoSummarized}<i class="fa fa-caret-down"></i></a>
                    <div class="collapse" id="demo5">
                        <a href="#" class="list-group-item">Subitem 1</a>
                        <a href="#SubSubMenu4" class="list-group-item strong" data-toggle="collapse" data-parent="#SubSubMenu4"><i class="glyphicon glyphicon-thumbs-up"></i> Subitem 2 <i class="fa fa-caret-down"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<#include "include/footer.ftl">