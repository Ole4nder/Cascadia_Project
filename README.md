<div style="text-align:center; text-decoration: underline;"> 
<h1> Projet de programmation orientée objet : Cascadia</h1>
<h2> ANTONI Jade et TWARDAWA Yanis</h2>
</div>

## <u>Sommaire :</u>

1. [Instructions de compilation et lancement](#instructions-de-compilation-et-lancement)
2. [Implémentation](#implémentation)
    - [Packages](#packages)
    - [Choix des classes](#choix-des-classes)
    - [Ressources](#ressources)
3. [Organisation du travail](#organisation-du-travail)
4. [Problèmes rencontrés](#problèmes-rencontrés)
5. [Améliorations possibles](#améliorations-possibles)

## <u>Instructions de compilation et lancement :</u>
> Pour plus d'informations, voire le manuel d'architecture "user.pdf" dans le répertoire docs.

Lancer le projet grâce à la commande ant, puis grâce à la commande java -jar Cascadia.jar, suivez les consignes du terminal afin d'ajouter les joueurs et la variante de jeu.

## <u>Implémentation :</u>
> Pour plus d'informations, voire le manuel d'architecture "dev.pdf" dans le répertoire docs.

### Packages :
Le projet est organisé en plusieurs packages et sous-packages.
Nous avons donc un package principal nommé fr.uge, contenant une multitude de sous-packages s'occupant de chaque aspect du jeu.
Nous avons donc séparés en packages : les animaux (tokens et cartes), les sacs de jetons et de tuiles (habitats et habitats de départ), le plateau de jeu, les parties, les graphismes, les intéractions des joueurs, le score, la pioche, et les tuiles.

### Choix des classes :
Nous avons longtemps débattu sur le choix entre record et class pour certaines classes.
En effet, les classes sont plus contraignantes que les records, mais sont parfois nécessaires.
Les types de chacunes des classes ont finies par être choisies pour maintenir au maximum une certaine modularité.
Par exemple, nous avons longtemps hésité à adapter la classe Game en un record, car la majorité de nos paramètres étaient finaux.
Cependant, un de nos paramètres principaux était le nombre de tours, qui était obligatoire et forcément amené à changer.
Mais nous avons tout de même fait en sorte d'avoir le plus de records possibles, pour une meilleure lisibilité du code, et selon les conseils de notre enseignante.

### Ressources :
Enfin, en plus du répertoire "lib" permettant l'utilisation de Zen6, nous avons prit la liberté de rajouter un répertoire "resources" dans le dossier "src".
Ce dernier contient nos fichiers textes nous permettant l'implémentation des cartes d'animaux ainsi que des tuiles du jeu.

## <u>Organisation du travail :</u>
Au début, nous avons fait la grande majorité du code ensemble. Que ce soit à l'université sur un même ordinateur, ou bien en appel via Discord.
Nous alternions la personne qui programmait sur son ordinateur, en faisant un partage d'écran pour l'autre. 
Grâce à ça, on se partageait nos idées en direct, et pouvait expliquer des choses à l'autre.

Les quelques fois où il n'était pas possible d'avoir des horaires communs, nous avons donc implémenter des choses de notre côté, tout en tenant au courant l'autre via Discord.
Dès que quelque chose était modifiée sur le projet, même minime, nous actualisions le GitHub de notre projet.

* <u>Pour la phase 1 et une partie de la phase 3 :</u>

    Seule de son côté, Jade s'est occupée des méthodes gérant les choix utilisateurs et la surpopulation de la pioche.

    Seul de son côté, Yanis s'est occupé des calculs des coordonnées et de la position relative pour les tuiles, et a réorganiser une partie du code pour mieux coller aux attentes. 
    
    Pendant ce temps là Jade, en vocal avec Yanis, s'est occupée des deux manuels et du rapport.

* <u>Pour la phase graphique (2) :</u>

    Yanis a été seul pour la grande majorité de cette phase suite aux problèmes personnels de Jade, listés en dessous.
    Il a donc implémenté toute la partie avec Zen6, tout en continuant à alimenter le GitHub afin de tenir Jade au courant de l'avancée du projet et puisse le questionner au besoin.
    Il est à l'origine de l'interface graphique et des interactions avec l'utilisateur via Zen6.

Pour le rendu final, les manuels d'utilisateurs et le README ont été écrits par Jade. 
Le build.xml a d'abord eu une ébauche par Yanis, puis rendu fonctionnel et finalisé à deux.

## <u>Difficultées rencontrés :</u>
Nous avons plusieurs fois modifier des classes en record et inversement. 
En effet, nous voulions que la phase 1 soit la plus parfaite possible, afin d'avoir une base solide.
Cependant, à cause de cela, nous avons perdu du temps à paufiner les détails, et n'avons pas pu commencer la phase 2 en temps et en heure.

De plus, il nous a fallu un long moment à relire, de nombreuses fois, les règles du jeu. Nous n'étions jamais tout à fait sûrs de l'utilisation de certains éléments du jeu, ou du déroulement d'un tour par exemple.

Nous avons également eu un problème avec une fonction qui levait, dans certains cas, une IndexOutOfBoundsException puisque nous itérions sur une collection que nous modifions en même temps (en enlevant des éléments).

Notre problème principal, c'est qu'on s'est rendu compte, le week-end du premier rendu, que notre implémentation des tuiles n'était pas bonne.
Pour calculer les coordonnées de nos tuiles carrées, on utilisait le coin en haut à gauche, et le coin en bas à droite.
C'est ce qui définissait toutes nos tuiles, mais cela n'allait pas fonctionner pour les tuiles hexagonales.
Il a fallu qu'on choisisse entre recommencer toute l'implémentation de 0, ou bien continuer sur notre lancé pour rendre une phase 1 fonctionnelle pour la première soutenance, mais qu'on changera juste après.
On a préféré repartir sur une bonne base, quitte à être en retard lors de la soutenance, mais avoir plus de choses à dire, et partir sur quelque chose de positif.
On a donc modifier une grosse partie des méthodes de notre package Tile, afin de recalculer les coordonnées, et la position relative des tuiles.

Suite à la soutenance, nous venions donc juste de refaire le code. Nous n'étions donc qu'à la phase 1. Nous avons continué à travailler un peu ensemble pour finir les bases, et commencer l'implémentation de la suite, comme les tuiles hexagonales, les joueurs, leurs plateaux etc.
Cependant, après cela, Jade a eu plusieurs problèmes personnels. Des problèmes de santé, l'hospitalisation de plusieurs de ses proches, ainsi que le décès et l'enterrement d'un membre de sa famille.
Elle a également eu des problèmes plus personnels, qu'elle a expliquée à Madame Claire David dès que possible, afin que l'administration soit au courant de son état de sa situation. 
Madame David accepte de recevoir tous étudiants ou enseignants ayant besoin de plus d'informations au sujet de Jade actuellement.

Malheureusement, son état a retardé grandement le travail, obligeant Yanis a travaillé seul pour une partie du projet afin que le projet soit fonctionnel et rendu dans les temps.

## <u>Améliorations possibles :</u>

A cause de tout cela, le projet n'est pas terminé.

Pour la phase 1, nous y avons passer beaucoup de temps, certains points restent à améliorer comme certaines de nos méthodes un peu longues ou certaines de nos implémentations qui pourraient être plus extensibles.
Cependant, nous avons des bases assez solides pour continuer à travailler dessus.

Parmis les améliorations possibles de notre projet, nous pourrions implémenter la phase 4 avec le mode solo et les réussites de Cascadia.
La phase 3 a été entammée, mais seule le choix des joueurs, ainsi que le choix entre la version terminale et graphique des tuiles carrées a été implémenté.
Il faudrait donc finir l'implémentation des tuiles hexagonales pour qu'elles soient fonctionnelles, malgré leur présence et leurs méthodes dans le code.
Ainsi que d'implémenter la fin de la variante normale et solo.

Nous pourrions également améliorer l'interface graphique, qui est très basique pour le moment.
Pour cela, nous pourrions, par exemple, rajouter un fond, rajouter des images pour les tuiles plutôt que le texte, montrer plus clairement le tour des joueurs etc.
