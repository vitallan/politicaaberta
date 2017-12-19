<#include "include/header.ftl">
<div id="fh5co-featured-section">
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2 text-center pt-6">
            <form action="/deputados" method="get">
                <input class="col-md-10" type="text" value="${query?if_exists}" name="query" id="query" />
                <input class="col-md-2 btn-success" type="submit" value="Buscar" />
            </form>
        </div>
        <#if deputies??>
        <div class="col-md-8 col-md-offset-2 text-center pt-6">
            <div class="row text-center">
                <div class="list-group">
                <#list deputies as deputy>
                    <a href="/deputados/${deputy.normalizedName}" class="list-group-item">${deputy.name}</a>
                </#list>
                </div>
                <p>Não encontrou seu deputado? O limite é de 5 resultados por busca, por motivos de performance. Tente refinar mais o nome do seu deputado.</p>
            </div>
        </div>
        </#if>
    </div>
</div>
</div>
<#include "include/footer.ftl">