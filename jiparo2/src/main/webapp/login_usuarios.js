        const formulario = document.getElementById('login_form');
        const btnEnviar = document.getElementById('btnEnviar');

        formulario.addEventListener('submit', async function(event){

            event.preventDefault();

            // Indicador de carga
            btnEnviar.disabled = true;
            btnEnviar.textContent = 'Verificando...';
            btnEnviar.classList.add('opacity-50');

            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;

            const datosValidacion = {
                  username: username,
                  password: password,
            };

            // Para depurar, yo
            console.log('Enviando al servidor:', datosValidacion);


            try {
                 // Enviar datos al servlet
                    const response = await fetch('/jiparo2/api/loginUsuarioServlet', {
                         method: 'POST',
                         headers: {
                                'Content-Type': 'application/json',
                         },
                         body: JSON.stringify({
                                    username: username,
                                    password: password
                         })
                         });
                            const rawText = await response.text();
                            console.log('Respuesta cruda:', rawText);
                            const data = JSON.parse(rawText);
                            //const data = await response.json();


                         if(data.exito){

                            if(data.redirectUrl){
                                window.location.href = data.redirectUrl;
                            } else{
                                console.warn('No se recibió la URL');
                            }

                         }

                        } catch (error){
                            //console.error('Error:',error);
                            alert('Error al enviar los datos: ' + error.message);
                        } finally {
                            // Restaurar botón
                            btnEnviar.disabled = false;
                            btnEnviar.innerHTML = '<i class="fa-solid fa-right-to-bracket"></i> Iniciar Sesión';
                        }
            });





