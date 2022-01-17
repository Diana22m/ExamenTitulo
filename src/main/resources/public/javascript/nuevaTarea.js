document.getElementById('imagen').onchange = evt => {
    var imagen=document.getElementById('imagen').files;
    if(imagen!=null){
        var img=document.getElementById('img');
        img.src=URL.createObjectURL(imagen[0]);
    }else{
        alert("NO HAY NADA SELECCIONADO");
    }
}

let params = new URLSearchParams(location.search);
var correo = params.get('correo');
document.getElementById('cancelar').addEventListener('click', cancelar);
function cancelar(e){
    window.location.href="/main?correo="+correo;
}
var blob;
axios.get('/usuarioByCorreo?correo='+correo)
    .then(function (response){
        console.log(response.data);
        usuario=JSON.parse(JSON.stringify(response.data));
        document.getElementById('agregar').addEventListener('click', agregar);
        function agregar(e){
            var titulo=document.getElementById('titulo').value;
            var contenido=document.getElementById('contenido').value;
            var img=document.querySelector('input[type=file]');
           
            if(img.files[0]!=null){

                var file = img.files[0],
                reader = new FileReader();
                reader.onloadend = function () {
                   
                    var b64 = reader.result.replace(/^data:.+;base64,/, '');
                    console.log(b64);
                    
                  
                  axios.post('https://todolist-dc.herokuapp.com/tareaNueva', {
                    idTarea: 0,
                    titulo: titulo,
                    imagen: b64,
                    contenido: contenido,
                    idUsuario: usuario.id,
                    completed: false
                })

                .then(function (response) {
                    console.log(response.data);
                    if(response.data==='SI'){
                        console.log(blob);
                        msg.innerHTML="Registro éxitoso.";
                        window.location.href="/main?correo="+correo;
                    }else{
                        msg.innerHTML="Error inesperado";
                    }
                    
                })
                .catch(function (error) {
                    console.log(error);
                });
            };
            reader.readAsDataURL(file);
            }else{
                axios.post('https://todolist-dc.herokuapp.com/tareaNueva', {
                            idTarea: 0,
                            titulo: titulo,
                            contenido: contenido,
                            idUsuario: usuario.id,
                            completed: false
                        })

                        .then(function (response) {
                            console.log(response.data);
                            if(response.data==='SI'){
                                console.log(blob);
                                msg.innerHTML="Registro éxitoso.";
                                window.location.href="/main?correo="+correo;
                            }else{
                                msg.innerHTML="Error";
                            }
                            
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
            }
        }
    })
    .catch(function (error) {
        console.log(error);
        alert(error);
    });