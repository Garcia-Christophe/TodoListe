# TodoListe

Application mobile Android Todo-List, développée en équipe de 2.

Lorsque l’utilisateur arrive sur l’application, reconnaissable par son logo, il voit la liste de toutes ses
tâches. L’application contient une base de données SQlite pour sauvegarder les tâches et les
contextes.

Le bouton « + » permet de créer une nouvelle tâche, il suffit de renseigner :
- Le nom (nécessaire)
- La date (nécessaire)
- Une durée : les heures, les minutes
- Un contexte : depuis ceux disponibles
- Une description
- Un lien : visualisable juste en dessous dans la WebView si le lien existe, et dans le navigateur
web par défaut si l’utilisateur clique sur le bouton *Loupe*

En cliquant normalement sur une de ses tâches, il peut voir et modifier celle-ci. En cliquant
longuement, il peut la supprimer (après confirmation). L’utilisateur peut cocher ou décocher une
case (action instantanément enregistrée) pour se souvenir de l’état de la tâche sans vouloir la
supprimer.

Une barre de recherche est disponible pour chercher une tâche par son nom, sa description, sa date,
sa durée et son contexte. A chaque fois que le texte de recherche est changé, l’application met à jour
la liste des tâches visibles.

Des options sont accessibles avec les 3 petits points en haut à droite de la page :
- « Tout supprimer » permet de supprimer l’entièreté de ses tâches (après confirmation)
- « Informations » permet de voir l’utilisation de l’application et les crédits
- « Contextes » permet d’accéder à ses contextes

En cliquant sur « Contextes », l’utilisateur voit la liste de tous ses contextes. Il peut en créer un en
fournissant un titre puis en appuyant sur le bouton ajouter. Il ne peut pas créer un contexte dont le
nom est déjà utilisé. En appuyant normalement sur un contexte, il verra sur la page principale la liste
des tâches correspondant à celui-ci. En appuyant longuement, il peut supprimer le contexte (après
confirmation). Seul le contexte « Autres » n’est pas supprimable. Ce contexte « Autres » est le
contexte par défaut des tâches. Il est également attribué automatiquement aux tâches lorsque leur
contexte est supprimé.

Des toasts apparaissent dans l’application :
- « Veuillez préciser le titre et la date. », lorsque l’utilisateur oublie de fournir le titre et/ou la
date lors de la création d’une tâche
- « Veuillez saisir une URL valide. », lorsque l’utilisateur souhaite cliquer sur le bouton
permettant d’accéder au lien de la tâche dans son navigateur web par défaut
- « Aucune tâche trouvée. », lorsque aucune tâche ne correspond à la recherche de
l’utilisateur
- « Ce contexte n’est pas supprimable. », lorsque l’utilisateur souhaite supprimer le contexte
« Autres »
- « Toutes les tâches ont été supprimées. », lorsque l’utilisateur a supprimé toutes les tâches
de l’application