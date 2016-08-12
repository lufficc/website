<#if msg??>
    <#if !messageType??>
        <#assign messageType="info">
    </#if>
<div class="alert alert-${messageType ! "info"} alert-dismissible" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
            aria-hidden="true">&times;</span></button>
${msg}
</div>
</#if>