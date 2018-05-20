# Application de calcul de taxes

## Domaine

Pour modéliser le besoin de l'énoncé les classes ci-dessous ont été créées.

### `Product`
`Product` représente un produit. 
Un produit a les caractéristiques suivantes :
 - **`name`** : nom du produit
 - **`type`** : type du produit. La liste des types possibles est définie dans l'énumation `Type`. Ces valeurs sont : `BOOK` (pour les livres), `FOOD` (pour la nourriture), `MEDICAL` (pour les médicaments) et `OTHER` (pour tout autre produit).
 - **`quantity`** : la quantity des produits
 - **`price`** : prix d'un produit
 - **`imported`** : indique si le produit est importé ou pas

### `ShoppingBag`
Un panier d'achat contient une liste des produits.
 - **`products`** : liste des produits qui composent le panier d'achat
 
### `Purchase`
Purchase représente un achat. 
Un achat a les caractéristiques suivantes :
- **`product`** : le produit acheté
- **`taxAmount`** : le montant des taxes appliquées pour l'achat du produit
- **`totalAmount`** : montant du prix d'achat (taxes comprises) 

### `Receipt`
Receipt représente un ticket de caisse. 
Il liste les achats effectués, le montant total des taxes et le montant total de la facture.
 - **`purchases`** : liste des achats
 - **`taxAmout`** : montant total des taxes pour tous les achats
 - **`totalAmount`** : montant total des achats (taxes incluses)

## Choix de définition du domaine 
La taxe n'est pas une propriété inhérente au produit, elle ne doit donc pas être définie dans la classe `Product`.
La classe `Purchase` permet de modéliser le calcul des taxes sur les produits. Elle permet aussi de calculer le prix total en tenant compte des taxes appliquées.

## Calcul des taxes
### Principe
Une taxe est appliquée sur certains types de produits mais pas sur d'autres. Une taxe spéciale est appliquée sur les produits importés.
Le calcul des taxes varie donc selon le type du produit et selon le fait qu'il soit importé ou pas.
Ce n'est pas le cas dans le cadre de cet énonce, mais il est aussi possible de définir d'autres taxes selon les produits.

Pour tout type de calcul, on applique un arrondi de 5 cent sur les montants. Cet arrondi est commun quelque que soit la taxe appliquée.

Une combinaison des patterns **Strategy** et **Template method** est utilisée pour permettre de définir des stratégies de calcul différentes, tout en définissant un traitement commun à toutes ces stratégies.

### Implémentation
L'interface `TaxCalculationStrategy` déclare la méthode `calculateTaxAmount` pour calculer la taxe du produit passé en paramètre.

La classe abstraite `DefaultTaxCalculationStrategy` implémente l'interface `TaxCalculationStrategy`. Dans l'implémentation de la méthode `calculateTaxAmount`, le pattern **Template method** est utilisé. 
Celui-ci permet de définir la structure de l'algorithme de calcul avec les traitements communs et les traitements spécifiques. Les traitements communs sont implémentés au niveau de la classe `DefaultTaxCalculationStrategy` et les traitements spécifiques sont laissés aux sous-classes (`LocalTaxCalculationStrategyImpl` et `ImportTaxCalculationStrategyImpl`) qui se chargent d'implémenter la méthode `calculateSpecificTaxAmount`.

Avec le pattern **Template method**, l'algorithme permet d'appliquer un arrondi de 5 cent quelque que soit la stratégie implémentée dans les sous-classes.

Ainsi, s'il n'existe aucun traitement commun à toutes les stratégies de calcul, l'utilisation du pattern **Template method** n'est pas nécessaire.

Par ailleurs, l'interface `TaxCalculationStrategy` peut être implémentée par d'autres classes sans hériter de `DefaultTaxCalculationStrategy` pour pour définir d'autres stratégies ne suivant pas l'agoritme défini dans celle-ci.

La classe `TaxCalculationStrategyFactory` implémente le pattern **Factory** pour associer une stratégie à un produit donné. L'implémentation de ce pattern est faite la méhtode `getTaxCalculationStrategy`.

## Formattage des montants

## Axes d'amélioration

## Conclusion

