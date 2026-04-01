const formulario = document.getElementById('form_crear_empresa');

const btnEnviar = document.getElementById('btn_event');

    // 1. Escuchar el evento 'submit' del formulario
    formulario.addEventListener('submit', async function(event) {

        event.preventDefault();

        btnEnviar.disabled = true;
        btnEnviar.textContent = 'Enviando...';
        btnEnviar.classList.add('opacity-50');

        const numero_empresa = document.getElementById('numero_empresa').value;
        const nombre_empresa = document.getElementById('nombre_empresa').value;
        const rfc = document.getElementById('rfc').value;
        const direccion_fiscal = document.getElementById('direccion_fiscal').value;
        const descripcion = document.getElementById('descripcion').value;



        // 4. Crear objeto JavaScript con los datos
        const datosEmpresa = {
            numero_empresa : numero_empresa,
            nombre_empresa: nombre_empresa,
            rfc: rfc,
            direccion_fiscal: direccion_fiscal,
            descripcion: descripcion,
        };

        console.log('Enviando al servidor:', datosEmpresa);

        try {
            // FETCH
            const response = await fetch('/jiparo2/api/ContabilidadServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json', // Enviamos JSON
                },
                body: JSON.stringify(datosEmpresa)  // Convertir objeto JS a texto JSON
            });

            // Verificar si la respuesta es correcta
            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }

            // Parsear la respuesta JSON que viene del servlet
            const respuestaServidor = await response.json();

            console.log('Respuesta del servidor:', respuestaServidor);

            if(respuestaServidor.exito){
                    window.location.href = '/jiparo2/contabilidad.html';
            } else {
                alert('Error al crear empresa: ' + respuestaServidor.mensaje);
            }

            // limpiar el formulario
            formulario.reset();

        } catch (error) {
            console.error('Error:', error);
            alert('Error al enviar los datos: ' + error.message);
        }
    });

    //***************************************************************************

/*
            // Función para obtener el usuario actual desde la sesión
            async function obtenerUsuarioActual() {
                try {
                    const response = await fetch('/api/ContabilidadServlet', {
                        method: 'GET',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        credentials: 'same-origin' // Importante: envía la cookie de sesión
                    });

                    if (!response.ok) {
                        if (response.status === 401) {
                            // No autorizado, redirigir al login
                            window.location.href = '/login.html';
                        }
                        throw new Error(`Error HTTP: ${response.status}`);
                    }

                    const usuario = await response.json();
                    return usuario;

                } catch (error) {
                    console.error('Error al obtener usuario:', error);
                    return null;
                }
            }
*/

//********************************************************************

const url = '/api/ContabilidadServlet';

fetch(url, )
    .then(response => response.json())
    .then(json => console.log(json));