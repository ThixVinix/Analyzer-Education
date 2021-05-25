var btn_add = document.getElementById('add');
var btn_buy = document.getElementById('buy');
var form1 = document.getElementById('form1');
var box = document.getElementById('box');

var contador = 0;

btn_add.addEventListener('click', function(){
    contador++;
    createLabelNomeTeste();
    createInputNomeTeste();
	createLabelEntradaTeste();
	createInputEntradaTeste();
	createLabelSaidaTeste();
	createInputSaidaTeste();
});

//<label for="nome">Produto: </label>
function createLabelNomeTeste()
{
    var elemento = document.createElement('label');
    elemento.setAttribute('for', 'nome_' + contador);
	elemento.setAttribute('class', 'col-form-label fs-6');
    elemento.textContent = 'Nome do teste ' + contador + ':';

    box.appendChild(elemento);
}

//<input type="text" name="nome" id="nome" autocomplete="off"/>
function createInputNomeTeste()
{
    var elemento = document.createElement('input');
    elemento.setAttribute('type', 'text');
    elemento.setAttribute('name', 'nome_' + contador);
    elemento.setAttribute('id', 'nome_' + contador);
    elemento.setAttribute('autocomplete', 'off');
    elemento.setAttribute('class', 'form-control');

    box.appendChild(elemento);
}

function createLabelEntradaTeste()
{
    var elemento = document.createElement('label');
    elemento.setAttribute('for', 'entrada_teste_' + contador);
	elemento.setAttribute('class', 'col-form-label col-6 fs-6');
    elemento.textContent = 'Entrada do teste ' + contador + ':';

    box.appendChild(elemento);
}

function createLabelSaidaTeste()
{
    var elemento = document.createElement('label');
    elemento.setAttribute('for', 'saida_teste_' + contador);
	elemento.setAttribute('class', 'col-form-label col-6 fs-6');
    elemento.textContent = 'Saída do teste ' + contador + ':';

    box.appendChild(elemento);
}

function createInputEntradaTeste()
{
    var elemento = document.createElement('textarea');
    elemento.setAttribute('type', 'text');
    elemento.setAttribute('name', 'input_entrada_teste_' + contador);
    elemento.setAttribute('id', 'input_entrada_teste_' + contador);
    elemento.setAttribute('autocomplete', 'off');
    elemento.setAttribute('class', 'form-control col-1');

    box.appendChild(elemento);
}

function createInputSaidaTeste()
{
    var elemento = document.createElement('textarea');
    elemento.setAttribute('type', 'text');
    elemento.setAttribute('name', 'input_saida_teste_' + contador);
    elemento.setAttribute('id', 'input_saida_teste_' + contador);
    elemento.setAttribute('autocomplete', 'off');
    elemento.setAttribute('class', 'form-control col-6');

    box.appendChild(elemento);
}

btn_buy.addEventListener('click', function(){
    form1.submit();
});

form1.addEventListener('submit', function(){
    alert(serialize(document.forms[0]));
});


function serialize(form){if(!form||form.nodeName!=="FORM"){return }var i,j,q=[];for(i=form.elements.length-1;i>=0;i=i-1){if(form.elements[i].name===""){continue}switch(form.elements[i].nodeName){case"INPUT":switch(form.elements[i].type){case"text":case"hidden":case"password":case"button":case"reset":case"submit":q.push(form.elements[i].name+"="+encodeURIComponent(form.elements[i].value));break;case"checkbox":case"radio":if(form.elements[i].checked){q.push(form.elements[i].name+"="+encodeURIComponent(form.elements[i].value))}break;case"file":break}break;case"TEXTAREA":q.push(form.elements[i].name+"="+encodeURIComponent(form.elements[i].value));break;case"SELECT":switch(form.elements[i].type){case"select-one":q.push(form.elements[i].name+"="+encodeURIComponent(form.elements[i].value));break;case"select-multiple":for(j=form.elements[i].options.length-1;j>=0;j=j-1){if(form.elements[i].options[j].selected){q.push(form.elements[i].name+"="+encodeURIComponent(form.elements[i].options[j].value))}}break}break;case"BUTTON":switch(form.elements[i].type){case"reset":case"submit":case"button":q.push(form.elements[i].name+"="+encodeURIComponent(form.elements[i].value));break}break}}return q.join("&")};