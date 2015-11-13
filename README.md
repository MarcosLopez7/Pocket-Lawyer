# Pocket-Lawyer
Proyecto para crear una aplicación en Android para ayudar a la gente a conocer las leyes de México

TABLAS:

Tabla Leyes:
Columna                   Tipo    Llave
id                        Integer PK
titulo                    TEXT    -
fecha_ultima_modificacion TEXT    -
numero_articulos          Integer -
link                      TEXT    -

Tabla Articulos:
Columna    Tipo    Llave
id         Integer PK
titulo     TEXT    -
resumen    TEXT    -
texto      TEXT    -
categoria  TEXT    -
beneficios TEXT    -
deberes    TEXT    -
id_ley     Integer FK
prioridad  TEXT    -

La estructuración está en Model/ArticuloReader.java
