var btnAcceder=document.getElementById('acceder');
var btnRegistrarse=document.getElementById('registrar');
var correo, clave;
btnAcceder.addEventListener('click', acceder);
function acceder(e){
    correo=document.getElementById('correo').value;
    clave=document.getElementById('clave').value;
    if(correo){
        if(clave){
            axios.post('https://todolist-dc.herokuapp.com/acceso', {
                correo: correo,
                clave: clave,
                completed: false
            })
            .then(function (response) {
                console.log(response.data);
                if(response.data==='SI'){
                    document.getElementById('msg').innerHTML=" correcto.";
                    alert('Redirigiendo al panel principal de resgistro.');
                    window.location.href="/main?correo="+correo;
                }else{
                    document.getElementById('msg').innerHTML="incorrecto.";

                }
            })
            .catch(function (error) {
                console.log(error);
            });
        }else{
            document.getElementById('msg').innerHTML="La clave es un campo requerido";
        }
    }else{
        document.getElementById('msg').innerHTML="El correo electrónico es un campo requerido";
    }
}
btnRegistrarse.addEventListener('click', registrarse);
function registrarse(e){
    window.location.href="/registrarme";
}