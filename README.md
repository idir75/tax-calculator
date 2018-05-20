# Application de calcul de taxes

## Domaine

Pour modéliser le besoin de l'énoncé, les classes ci-dessous ont été créées.

### `Product`
`Product` représente un produit. Celui-ci a les caractéristiques suivantes :
 - **`name`** : nom du produit
 - **`type`** : type du produit. La liste des types possibles est définie dans l'énumération `Type`. Ces valeurs sont : `BOOK` (pour les livres), `FOOD` (pour la nourriture), `MEDICAL` (pour les médicaments) et `OTHER` (pour tout autre produit).
 - **`quantity`** : la quantité des produits
 - **`price`** : prix d'un produit
 - **`imported`** : indique si le produit est importé ou pas

### `ShoppingBag`
`ShoppingBag` représente un panier d'achat. Celui-ci contient une liste des produits.
 - **`products`** : liste des produits qui composent le panier d'achat
 
### `Purchase`
`Purchase` représente un achat. Celui-ci a les caractéristiques suivantes :
- **`product`** : le produit acheté
- **`taxAmount`** : le montant des taxes appliquées pour l'achat du produit
- **`totalAmount`** : montant du prix d'achat (taxes comprises) 

### `Invoice`
`Invoice` représente la facture. 
Celle-ci contient liste les achats effectués, le montant total des taxes et le montant total à payer.
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

Pour tout type de calcul, on applique un arrondi de 5 cents supérieurs sur les montants. Cet arrondi est commun quelque que soit la taxe appliquée.

Le pattern **Strategy** est associé au pattern **Template method** pour permettre de définir des stratégies de calcul différentes tout en permettant d'avoir un traitement commun à toutes ces stratégies.

### Implémentation
L'interface `TaxCalculationStrategy` déclare la méthode `calculateTaxAmount` pour calculer la taxe du produit passé en paramètre.

La classe abstraite `DefaultTaxCalculationStrategy` implémente l'interface `TaxCalculationStrategy`. Dans l'implémentation de la méthode `calculateTaxAmount`, le pattern **Template method** est utilisé.
Celui-ci permet de définir la structure de l'algorithme de calcul avec les traitements communs et les traitements spécifiques. Les traitements communs sont implémentés au niveau de la classe `DefaultTaxCalculationStrategy` (on évite ainsi de dupliquer le code dans les sous-classes), et les traitements spécifiques sont laissés aux sous-classes (`LocalTaxCalculationStrategyImpl` et `ImportTaxCalculationStrategyImpl`) qui se chargent d'implémenter la méthode `calculateSpecificTaxAmount`.

Ainsi, comme l'opération d'arrondi de 5 cents supérieurs est commune à toutes les stratégies, elle est définie au niveau de la classe abstraite `DefaultTaxCalculationStrategy`.

Sans ce traietment commun, l'utilisation du pattern **Template method** n'est pas nécessaire.

Par ailleurs, on peut définir d'autres classes stratégies qui implémentent l'interface `TaxCalculationStrategy` mais n'héritent pas de la classe `DefaultTaxCalculationStrategy`, et définir ainsi un algorithme de calcul complètement différent.

La classe `TaxCalculationStrategyFactory` utilise le pattern **Factory** pour associer une stratégie à un produit donné. L'implémentation de ce pattern est faite dans la méthode `getTaxCalculationStrategy`.

## Génération de la facture
La classe `InvoiceGeneratorImpl` qui implémente l'interface `InvoiceGenerator` crée une facture (`Invoice`) à partir d'un panier (`ShoppingBag`) passé en paramètre. Pour cela, elle crée une liste d'achats (`Purchase`) à partir de la liste des produits contenu dans le panier.

La méthode calcule également le montant total des taxes sur tous les achats ainsi que le montant total de la facture.

La méthode interne `getPurchase` crée un achat à partir du produit passé en paramètre. Elle fait appel à la factory `TaxCalculationStrategyFactory` pour déterminer la stratégie à appliquer pour la calcul.

## Gestion des montants
La classe BigDecimal est utilisée pour manipuler les montants, car elle garantit une meilleure précision par rapport aux autres types comme Double (ou double) ou Float (ou float).

### `DecimalRounder`
La classe `DecimalRounder` a les caractéristiques suivantes :
 - `scale` : nombre de décimales après la virgule. La valeur est de 2 par défaut.
 - `roundingRate` : le taux d'arrondi appliqué. Dans le cas de l'énoncé, il est de 5 cents.
 - `roundingMode` : le mode d'arrondi. Dans le cas de l'énoncé, il est demandé d'utiliser l'arrondi supérieur.
 
La classe `DecimalRounder` implémente le calcul de l'arrondi de `roundingRate` cents supérieurs dans la méthode `round`. Cette méthode permet aussi d'appliquer un nombre de décimale après la virgule.

Les classes de calcul des taxes (`DefaultTaxCalculationStrategy` et les stratégies `LocalTaxCalculationStrategyImpl` et `ImportTaxCalculationStrategyImpl`) utilisent un objet de type `DecimalRounder` à l'instanciation.
La gestion des arrondis n'est donc pas faite directement par l'algorithme, mais déléguée à une instance de `DecimalRounder`.

Il est donc possible de modifier la logique d'arrondi dans la classe `DecimalRounder` sans impacter le calcul des taxes.

### Algorithme de calcul de l'arrondi
Pour calculer l'arrondi 5 cents supérieurs, l'algorithme utilisé est le suivant :
 - Étape 1 : calculer le taux d'arrondi en centimes : 5 cents correspond à 5%, 7 cents correspond à 7%, etc.
 ```
 BigDecimal percentage = roundingRate.divide(DefaultConstants.ONE_HUNDRED);
 ```
 
 - Étape 2 : calculer le ratio du montant à arrondir par rapport au taux et l'arrondir le résultat obtenu. La méthode `setScale` permet d'arrondir selon le mode d'arrondi `roundingMode`
 ```
 BigDecimal ratio = p_amount.divide(percentage).setScale(0, roundingMode);
 ```
 
 - Étape 3 : multiplier le ratio par le taux d'arrondi calculé à l'étape 1.

## Technologies
 - Java : 1.8.0_151
 - Maven : 3.5.3

## Utilisation
La classe de test est `TaxCalculator`.
Pour exécuter le programme, il faut : 
 - Ajouter la déclaration d'un JDK 1.8 dans la variable PATH
 - Lancer le fichier `tax-calculation.bat` en ligne de commande

