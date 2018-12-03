
1 - Definir et configurer les accès aux bases de données au niveau du fichier context.xml

2 - Ajouter une reference aux bases de données au niveau du descripteur de déploiement web.xml


3 - Configurer les Unités de persistence au niveau fichier persistence.xml. A noter que Tomcat ne support pas JTA 
    et du coup l'attribut 'transaction-type doit etre positionné à 'RESOURCE_LOCAL'