<div style="text-align:center; text-decoration: underline;"> 
<h1> Projet de programmation orientée objet : Cascadia</h1>
<h2> ANTONI Jade et TWARDAWA Yanis</h2>
</div>

## <u>Sommaire :</u>

1. [Introduction](#introduction)
2. [Instructions de compilation et lancement](#instructions-de-compilation-et-lancement)
3. [Implémentation](#implémentation)
    - [Packages](#packages)
    - [Choix des classes](#choix-des-classes)
    - [Ressources](#ressources)
4. [Organisation du travail](#organisation-du-travail)
5. [Problèmes rencontrés](#problèmes-rencontrés)
6. [Améliorations possibles et futures implémentations](#améliorations-possibles-et-futures-implémentations)

## <u>Introduction :</u>
Ce projet consistait à reproduire, en Java, le jeu Cascadia; un jeu de plateau stratégique visant à construire un écosystème.
Nous avons donc dû apprendre à utiliser différents objets afin de nous adapter à chaque éléments du jeu.
De plus, nous avons veillé à rendre notre code le plus générique possible, afin d'assurer une base solide et flexible pour les futures implémentations, minimisant, ainsi, toutes les modifications futures.

## <u>Instructions de compilation et lancement :</u>
> Pour plus d'informations, voire le manuel d'architecture "user.pdf" dans le répertoire docs.

Lancer le projet grâce à la commande java -jar Cascadia.jar, suivez les consignes du terminal afin d'ajouter les joueurs et la variante de jeu.

## <u>Implémentation :</u>
> Pour plus d'informations, voire le manuel d'architecture "dev.pdf" dans le répertoire docs.

### Packages :
Le projet est organisé en plusieurs packages et sous-packages.
Nous avons donc un package principal nommé fr.uge, qui contient notre Main. 
A l'intérieur de ce dernier, une multitude de sous-packages gèrent chaque aspect du jeu : tout ce qui concerne les animaux, les joueurs, les tuiles et enfin les fonctionnalités du jeu en lui-même.

### Choix des classes :
Nous avons longtemps débattu sur le choix entre record et class pour certaines classes. 
En effet, les classes sont plus contraignantes que les records, mais sont parfois nécessaires.
Les classes ont finies par être choisies pour maintenir au maximum une certaine modularité.

### Ressources :
Enfin, nous avons rajouté un dossier "resources" dans le dossier source.
Ce dernier contient tous nos fichiers externes. 

Pour l'instant, il contient les fichiers textes définissant les éléments du jeu tels que les différentes cartes, les tuiles de jeu ou encore les tuiles de départ.
Il contiendra également les images tirées des photographies de Bosphore pour la partie graphique.

## <u>Organisation du travail :</u>
Dès le début, nous avons fait la grande majorité du code ensemble. Que ce soit à l'université sur un même ordinateur, ou bien en appel via Discord.
Nous alternions la personne qui programmait sur son ordinateur, en faisant un partage d'écran pour l'autre. 
Grâce à ça, on se partageait nos idées en direct, et pouvait expliquer des choses à l'autre.

Les quelques fois où il n'était pas possible d'avoir des horaires communs, nous avons donc implémenter des choses de notre côté, tout en tenant au courant l'autre via Discord.
Dès que quelque chose était modifiée sur le projet, même minime, nous actualisions le GitHub de notre projet.

Tous les fichiers ont donc été créés ensemble, les fonctions, quant à elles, ont majoritairement été faites en commun.

De son côté, Jade s'est occupée des méthodes gérant les choix utilisateurs et la surpopulation.

De son côté, Yanis s'est occupé des calculs des coordonnées et de la position relative pour les tuiles, tout en réorganisant le code suite aux problèmes que nous avons rencontré ce week-end. Pendant ce temps là Jade, en vocal avec Yanis, s'est occupée des manuels et du rapport.


## <u>Problèmes rencontrés :</u>
Nous avons plusieurs fois modifier des classes en record et inversement. 
En effet, nous voulions que la phase 1 soit la plus parfaite possible, afin d'avoir une base solide.
Cependant, à cause de cela, nous avons perdu du temps à paufiner les détails, et n'avons pas pu commencer la phase 2 en temps et en heure.

De plus, il nous a fallu un long moment à relire, de nombreuses fois, les règles du jeu. Nous n'étions jamais tout à fait sûrs de l'utilisation de certains éléments du jeu, ou du déroulement d'un tour par exemple.

Nous avons également eu un problème avec une fonction qui levait, dans certains cas, une IndexOutOfBoundsException puisque nous itérions sur une collection que nous modifions en même temps (en enlevant des éléments).

Notre problème principal, c'est qu'on s'est rendu compte ce week-end que notre implémentation des tuiles n'était pas bonne.
Pour calculer les coordonnées de nos tuiles carrées, on utilisait le coin en haut à gauche, et le coin en bas à droite.
C'est ce qui a défini toutes nos tuiles. 
Cependant, on vient de s'appercevoir que cela ne sera pas du tout efficace pour les tuiles hexagonales.
Il a fallu qu'on choisisse entre recommencer toute l'implémentation de 0, ou bien continuer sur notre lancé pour rendre une phase 1 fonctionnelle pour la soutenance, mais qu'on changera juste après.
On a préféré repartir sur une bonne base, quitte à être en retard lors de la soutenance, mais avoir plus de choses à dire, et partir sur quelque chose de positif.
On a donc modifier une grosse partie des méthodes de notre package Tile, afin de recalculer les coordonnées, et la position relative des tuiles.

## <u>Améliorations possibles et futures implémentations :</u>
Certaines fonctions peuvent devenir plus génériques, afin d'améliorer l'implémentation des tuiles hexagonales pour la phase 3.
Il faudra qu'on refinisse totalement la phase 1. Les fonctionnalités sont prêtes, mais comme nous avons dû modifier les tuiles, elles ne sont pas tout à fait prêtes.

Puis, qu'on implémente toute la partie graphique, les tuiles hexagonales, ainsi que les jetons natures.
Nous devons également ajouté le mode solo, et finir d'implémenter les variantes.
Enfin, nous ajouterons les succès de Cascadia.