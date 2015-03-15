_Informations sur les algorithmes génétiques_

_Graphe non orienté ?_

# Liste des paramètres #

  * Le nombre  d’individus : est le nombre de le voyageur qui "recherchent" le plus court chemin.
  * Le taux de reproduction : est le nombre de nouveaux individus pour 100 dans l’ancienne génération créés par croisement des meilleurs individus.
  * La probabilité de mutation d’un individu : nombre compris entre 0 et 1. Il reflète la probabilité d’un individu au sein de la population de pouvoir muter (0 = pas de mutation d’individu).
  * La probabilité de mutation d’un gène : nombre compris entre 0 et 1. Il reflète la probabilité d’un gène au sein d’un individu de pouvoir muter (0 = pas de mutation de gène).
  * Nombre de stagnation avant arret
  * Nombre de genes du probleme
  * Valeur d'adaptation maximum
  * Distance correspondante
  * Numéro de génération
  * Nombre d'individus testé

# Étapes de l'algorithme _tirer de interstices_ #
Initialisation d'une population

  * Évaluation : Tout d’abord, chaque individu est évalué. Pour cela, on calcule la valeur de la fonction objectif, c’est-à-dire la longueur du cycle parcouru par le voyageur de commerce.
  * Sélection : hasard ou les n meilleurs
  * Croisement : plusieurs techniques dont le crossover. Recopier la première partie du parent 1 puis la seconde partie du parent 2 mais ne pas recopier les villes déjà utilisés. Mettre les villes non utilisés à la fin.
  * Mutation : modification aléatoire d'une petite partie de certains individus (faible probabilité et souvent inversion de deux villes consécutives)


# Liens Internet #
  * http://interstices.info/jcms/c_37686/le-probleme-du-voyageur-de-commerce
  * http://labo.algo.free.fr/pvc/algorithme_genetique.html
  * http://sis.univ-tln.fr/~tollari/TER/AlgoGen1/node5.html
  * http://magnin.plil.net/anciensite/coursag/voyageur/voyageur.html
  * http://www.cppfrance.com/codes/PROBLEME-VOYAGEUR-COMMERCE-RESOLUTION-ALGORITHME-GENETIQUE-DEVCPP_27243.aspx
  * http://files.codes-sources.com/fichier.aspx?ID=50777
  * http://magnin.plil.net/spip.php?article33
  * http://magnin.plil.net/spip.php?rubrique8&debut_articles=5#pagination_articles
  * http://www.cppfrance.com/codes/INTELLIGENCE-ARTIFICIELLE-ALGO-GENETIQUE-VOYAGEUR-COMMERCE-AVEC-QT_17731.aspx
  * http://www.javafr.com/codes/APPLET-VOYAGEUR-COMMERCE-ALGORITHMES-GENETIQUES_41117.aspx