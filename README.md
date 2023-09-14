# GymFit-Proyect
En este proyecto tuvimos que realizar, en grupo, una aplicación para un gimnasio, en Java.

GUIA DE USO APLICACIÓN GYMFIT

(Ana Gomez Llaneza, Esther Martinez Blanco y Celia Martin Alvarez)


Al ejecutar la aplicación, si no se cargan los datos, sólo está formada por un administrador, el cual tiene que  introducir el usuario “admin” y  la contraseña “admin” para poder acceder a la misma.

Por otro lado, si se desea crear un usuario, en la ventana de login aparece un apartado donde pone Regístrate. Una vez que el cliente se registra, se realiza el pago de la tarifa plana.

Asimismo, si se quiere crear un monitor, eso se debe hacer desde el administrador, por lo tanto habría que iniciar sesión como administrador y desde ahí registrarlo.

Para que resulte más sencillo probar la aplicación, hemos creado un gymmain que contiene monitores, Tipos de Actividad, Clientes. Además también hay salas, tanto simples como compuestas, actividades grupales, sesiones grupales, planes personalizados, sesiones personalizadas, máquinas y material.
Para poder cargarlo es necesario ejecutar en primer lugar el gymmain y luego el main de la carpeta gui.

Además la aplicación cuenta con una copia de seguridad que se realiza al dar al botón de cerrar sesión desde cualquiera de los usuarios, por lo tanto a la hora de volver a abrir la aplicación se cargará con los datos que se habían creado en la anterior ejecución. Si  se desea borrar los datos y que el gimnasio vuelva a estar vacío, lo único que hay que hacer es dar al botón de borrar backup que se encuentra en la ventana de iniciar sesión, cerrar la aplicacion, por si acaso hacer un refresh de resources por si no se hubiese borrado correctamtente y volver a ejecutar el main.

Por una parte, en cuanto a la funcionalidad del cliente, encontramos los datos del usuario, las actividades grupales. Si te apuntas a la lista de espera podrás ver los demás usuarios que  están apuntados en la lista de espera. Si se desapunta un usuario de esa actividad en la que estás en la lista de espera en el apartado de notificaciones le llegará una a todos los usuarios que están en la lista de espera.
Asimismo, podrá apuntarse a planes personalizados si cumple los requisitos.
Para ver los cobros y devoluciones que le han hecho al cliente, puede acceder al apartado de notificaciones.
Por último, podrá consultar las reservas que ha realizado y si lo desea cancelar alguna.


Por otro lado, en cuanto a la funcionalidad del monitor, puede consultar los datos del monitor así  como sus actividades grupales.
Por otro lado en el apartado de mis planes personalizados podrá seleccionar una fila y cancelar tanto sesiones como planes y añadir sesiones grupales ya creadas o crear sesiones y planes personalizados.
Asimismo, el monitor podrá seleccionar una máquina, tanto alquilada como de propiedad y marcarla como averiada.

Finalmente, si se le cancela un plan personalizado al monitor, recibirá una notificación.


Por último, en cuanto a la funcionalidad del administrador, podrá consultar los beneficios del gimnasio, para ello seleccionará un mes y un año y le dará al botón de actualizar tabla, para que le aparezcan todas las sesiones de ese mes. Para calcular el beneficio tendrá que seleccionar todas las sesiones que quiere sumar, eso se realiza dando al ctr a la vez que seleccionas con el ratón, en el caso de que no vayan seguidas las filas, para ahorrarte tiempo si tienes que seleccionar varias seguidas seria con la tecla shift y seleccionando con el ratón y finalmente dar al botón de calcular beneficio.

Por otro lado, para configurar una sala, se recomienda primero seleccionar si es sala general o subsala, porque los datos a rellenar cambian según lo seleccionado.
Además podrá crear una actividad grupal y sesiones libres, hay que tener en cuenta la hora y fecha a la que se crea si es anterior a la fecha actual dará error, así como que no haya más sesiones en la misma sala a la misma hora. También podrá consultar las reservas de los clientes y los planes de los monitores. 
Asimismo, en el apartado de consultar actividades grupales del monitor podrá cancelar tanto sesiones como actividades, seleccionando la que desea eliminar y crear sesiones.
También puede crear un tipo de actividad, configurar el sueldo de los monitores, configurar los precios y configurar las penalizaciones a los clientes (que por defecto tienen un valor predeterminado). 
Finalmente podrá dar de alta tanto material como máquinas, podrá cambiar el estado de las máquinas, en primer lugar seleccionando si es alquilada o de propiedad y pudiendo cambiar su estado a operativa, en reparación o retirada. Para ver el gasto de la equipación tendrá que realizar lo mismo que para consultar el beneficio del gimnasio, es decir, seleccionar el mes que quiere ver, actualizar la tabla y seleccionar las equipaciones que quiere sumar.
