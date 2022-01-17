let params = new URLSearchParams(location.search);
var correo = params.get('correo');
var usuario;
var titulo=document.getElementById('titulo');
document.getElementById('salir').addEventListener('click', salir);
document.getElementById('agregar').addEventListener('click', agregar);

function salir(e){
    window.location.href="/login";
}

function agregar(e){
    window.location.href="/nuevaTarea?correo="+correo;
}
axios.get('https://todolist-dc.herokuapp.com/usuarioByCorreo?correo='+correo)
    .then(function (response){
        console.log(response.data);
        usuario=JSON.parse(JSON.stringify(response.data));
        titulo.innerHTML='Las tareas de '+usuario.nombres+' '+usuario.apellidos;
        p_data_user=document.createElement('p');
        p_data_user.innerHTML='| Email: '+usuario.correo+' | Edad: '+usuario.edad+' |';
        document.getElementById('user_data').appendChild(p_data_user);
        axios.get('/tareasByUsuario?idUsuario='+usuario.id)
        .then(function (response){
            console.log(response.data);
            var tareas=JSON.parse(JSON.stringify(response.data));
            var div_tareas=document.getElementById('tareas');
            if(tareas!=null){
                tareas.forEach(element => {
                    //console.log(element.idTarea);
                    var div_tarea=document.createElement('div');
                    div_tarea.style.border="solid #ffcc99";
                    var h3_titulo=document.createElement('h3');
                    h3_titulo.innerHTML=element.titulo;
                    var p_contenido=document.createElement('p');
                    p_contenido.innerHTML=element.contenido;
                    var img=document.createElement('img');
                    console.log(element.imagen);
                    
                    div_tarea.appendChild(h3_titulo);
                    div_tarea.appendChild(p_contenido);
                    if(element.imagen!='no hay imagen cargada'){
                        img.src = 'data:image/png;base64,'+element.imagen;
                        img.style.width="200px";
                        div_tarea.appendChild(img);
                    }
                    var br_space=document.createElement('br');

                    div_tareas.appendChild(div_tarea);
                    div_tareas.appendChild(br_space);
                    
                });
            }else{
                var msg=document.createElement('h2');
                msg.innerHTML="No hay ninguna tarea registrada."
                div_tareas.appendChild(msg);
            }
        })
        .catch(function (error) {
            console.log(error);
            alert(error);
        });
    })
    .catch(function (error) {
        console.log(error);
        alert(error);
    });