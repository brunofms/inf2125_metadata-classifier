function changeSelect (objSelect) {
	if (objSelect) {
		if (objSelect.options.selectedIndex > 0) {
			objSelect.form.submit();
		}
	}
}

function changeTipoProducao(obj) {

	if (obj) {

		var divParaAparecer = obj.value;
		var divParaSumir;
		if (divParaAparecer == 'P') {
			divParaSumir = "O";
		} else {
			divParaSumir = "P";
		}
		document.getElementById(divParaSumir).style.display = 'none';
		document.getElementById(divParaAparecer).style.display = 'block';
	}
}

function validaFormularioProducaoAcademica(frm) {
	var retorno = false;

	if (frm.rad_tipo[0].checked == true) { //P

		//validar titulo, autores, conferencia e ano
		if (validaCampo(frm.txt_titulo, "Informe o título da publicação.")) {
			if (validaCampo(frm.sel_autores, "Informe pelo menos um autor para a publicação.")) {
				if (validaCampo(frm.txt_conferencia, "Informe a conferência da publicação")) {
					if (validaCampo(frm.txt_ano, "Informe o ano da publicação")) {
						if (validatePosInt(frm.txt_ano)) {
							retorno = true;
						} else {
							alert("Ano inválido.");
						}
						
					}
				}
			}
		}

	} else { //O

		//validar trabalho, orientador e orientado
		if (validaCampo(frm.txt_trabalho, "Informe o trabalho da orientação.")) {
			if (validaCampo(frm.sel_orientadores, "Informe o orientador.")) {
				if (validaCampo(frm.sel_orientados, "Informe o orientado")) {
					retorno = true;
				}
			}
		}
	}
	return retorno;
}

function validaCampo(campo, mensagem) {
	var retorno = true;
	if (isCampoVazio(campo)) {
		alert(mensagem);
		retorno = false;
	}

	return retorno;
}

function isCampoVazio(campo) {
	if (campo.type == 'text') {
		return (campo.value == "");
	} else if (campo.type == 'select-multiple') {
		return (campo.options.selectedIndex == -1);
	} else if (campo.type == 'select-one') {
		return (campo.options.selectedIndex <= 0);
	}
	return true;
}

function validatePosInt(campo) {
	var s = campo.value;
    return(isPositiveInteger(s))
}

function isPositiveInteger (s)
{   var secondArg = false;

    return (isSignedInteger(s, secondArg)
       && ( (isEmpty(s) && secondArg)  || (parseInt (s) > 0) ) );
}
function isSignedInteger (s)

{   if (isEmpty(s))
   if (isSignedInteger.arguments.length == 1) return false;
   else return (isSignedInteger.arguments[1] == true);

   else {
      var startPos = 0;
      var secondArg = false;

      if (isSignedInteger.arguments.length > 1)
         secondArg = isSignedInteger.arguments[1];

      // skip leading + or -
      if ( (s.charAt(0) == "-") || (s.charAt(0) == "+") )
         startPos = 1;
      return (isInteger(s.substring(startPos, s.length), secondArg))
   }
}
function isInteger (s)
{
   var i;

   if (isEmpty(s))
   if (isInteger.arguments.length == 1) return 0;
   else return (isInteger.arguments[1] == true);

   for (i = 0; i < s.length; i++)
   {
      var c = s.charAt(i);

      if (!isDigit(c)) return false;
   }

   return true;
}

function isEmpty(s)
{
   return ((s == null) || (s.length == 0))
}

function isDigit (c)
{
   return ((c >= "0") && (c <= "9"))
}

function trocaItemSelectParticipante( select1_obj, select2_obj )
{
// Declara as variáveis
var option_obj                     // Objeto auxiliar option
var posicao_num                    // Acumula a posição a ser trocada
var indice_num                     // ndice para varrer os options
var valor_txt                      // Acumula a 
var texto_txt                      // Objeto auxiliar option
var lang

	// Testa se a lista está com pelo menos um item selecionado
	if ( select1_obj.selectedIndex != -1 )
	{
		tam = select1_obj.length;
		i = 0;
	    while (i < tam) {
			if (select1_obj.options[i].selected == true) {
				// Cria o objeto auxiliar option
				option_obj = document.createElement( 'OPTION' );

				// Informações do item selecionado da lista 1, e que sera retirado
				indice_num = i;  
				valor_txt  = select1_obj.options[indice_num].value;
				texto_txt  = select1_obj.options[indice_num].text;
				lang = select1_obj.options[indice_num].lang;

				// Remove o item seleciodado e acerta o contador
				select1_obj.remove( indice_num );
				i = i - 1;
				tam = tam - 1;

				// Adiciona o item na lista 2
				option_obj.text  = texto_txt;
				option_obj.value = valor_txt;
				option_obj.lang = lang;
				select2_obj.add(option_obj, null);
			}
			i = i + 1;
		}
	}
}
function validaFormularioAlocacaoParticipante(frm) {
	var retorno = false;
	var quantidadeProfessores = 0;

	for (var i=0; i< frm.sel_participantes.length; i++) {
		var objOption = frm.sel_participantes.options[i];
		if (objOption.lang == "true") {
			quantidadeProfessores++;
		}
		objOption.selected = true;
	}
	if (quantidadeProfessores > 0) {
		retorno = true;
	} else {
		alert("Selecione ao menos um professor.");
	}

	return retorno;
}