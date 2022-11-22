/* Socio 1 */
INSERT INTO SOCIOS (nombre, apellidos) VALUES('Roberto', 'López García');

/* Socio 2 */
INSERT INTO SOCIOS (nombre, apellidos) VALUES('Fátima', 'Galaup Cid');

/* Socio 3 */
INSERT INTO SOCIOS (nombre, apellidos) VALUES('María', 'Beviá Moreno');

/* Socio 4 */
INSERT INTO SOCIOS (nombre, apellidos) VALUES('Alberto', 'Fernández Sáez');

/* Socio 5 */
INSERT INTO SOCIOS (nombre, apellidos) VALUES('Adrián', 'Murillo Erviti');

/* Libro 1 */
INSERT INTO LIBROS (titulo, autor, sinopsis, portada, fecha_adquisicion, expurgado, socio_id) VALUES('Desarrollo y programación en entornos web', 'José Luis López Goytia', 'En este libro de texto de programación web se describen las herramientas y especificaciones para programación, los lenguajes de programación para el desarrollo web y el contexto de los sistemas operativos existentes.', null, '2022-11-05', 0, 3);

/* Libro 2 */
INSERT INTO LIBROS (titulo, autor, sinopsis, portada, fecha_adquisicion, expurgado, socio_id) VALUES('Curso de desarrollo Web con HTML, CSS y JavaScript', 'Mario Rubiales Gómez', 'HTML, CSS y JavaScript son las tres tecnologías básicas en las que se sustenta el desarrollo de páginas web. Este libro le servirá de ayuda a la hora de adentrarse en el uso y aprendizaje de estas tecnologías.', null, '2022-11-06', 1, null);

/* Libro 3 */
INSERT INTO LIBROS (titulo, autor, sinopsis, portada, fecha_adquisicion, expurgado, socio_id) VALUES('Desarrollo Web en entorno servidor', 'Juan Luis Vicente Carro', 'El libro Desarrollo Web en entorno servidor está dirigido tanto a los alumnos que cursan un ciclo de grado superior de informática como a profesionales del sector de las tecnologías de la información.', null, '2022-11-07', 0, null);

/* Libro 4 */
INSERT INTO LIBROS (titulo, autor, sinopsis, portada, fecha_adquisicion, expurgado, socio_id) VALUES('Desarrollo de Videojuegos - Diseño a comercialización', 'Ydhiner Moreno', 'La industria de los videojuegos crece cada día y las herramientas para su desarrollo son cada vez más accesibles, lo que ha impulsado a una gran cantidad de entusiastas y estudios indies a desarrollar sus propios productos.', null, '2022-11-08', 0, null);

/* Libro 5 */
INSERT INTO LIBROS (titulo, autor, sinopsis, portada, fecha_adquisicion, expurgado, socio_id) VALUES('Desarrollo Web con React', 'Pello Xabier Altadill Izura', 'El desarrollo Web es uno de los entornos más importantes de la programación y sin duda uno de los más cambiantes. El frontEnd se ha convertido en una jungla donde distintos frameworks de desarrollo compiten por ofrecer la mejor solución.', null, '2022-11-09', 1, null);
