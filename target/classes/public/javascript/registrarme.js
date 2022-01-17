var btnAcceder=document.getElementById('acceder');
var btnRegistrarse=document.getElementById('registrarme');
btnRegistrarse.addEventListener('click', registrarme);
var nombres, apellidos, correo, clave, edad;
var msg=document.getElementById('msg');
function registrarme(e){
    nombres=document.getElementById('nombres').value;
    apellidos=document.getElementById('apellidos').value;
    correo=document.getElementById('correo').value;
    clave=document.getElementById('clave').value;
    edad=document.getElementById('edad').value;
    if(nombres){
        if(apellidos){
            if(correo){
                if(clave){
                    if(edad){
                        msg.innerHTML="";
                        axios.post('https://todolist-dc.herokuapp.com/usuarioNuevo', {
                            nombres: nombres,
                            apellidos: apellidos,
                            correo: correo,
                            clave: clave,
                            edad: edad,
                            completed: false
                        })

                        .then(function (response) {
                            console.log(response.data);
                            if(response.data==='SI'){
                                msg.innerHTML="Registro ha sido éxitoso.";
                            }else{
                                msg.innerHTML="EL correo ha sido registrado.";
                            }
                            
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                    }else{
                        msg.innerHTML="Escribe tu edad";
                    }
                }else{
                    msg.innerHTML="Escribe tu clave";
                }
            }else{
                msg.innerHTML="Escribe tu correo";
            }
        }else{
            msg.innerHTML="Escribe tus apellidos";
        }
    }else{
       msg.innerHTML="Escribe tu nombre";
    }
}
btnAcceder.addEventListener('click', acceso);
function acceso(e){
    window.location.href="/login";
}