# Object Leaf Operations

The `ObjectLeafOperator` class provides utility operations for working with `ObjectLeaf` instances, including merging multiple objects and extracting typed values from leaves.

---

## English

### Merging Object Leaves

The `merge` method combines multiple `ObjectLeaf` instances into a single `ObjectLeaf`. When duplicate keys exist, the last value takes precedence.

#### Usage

```java
ObjectLeaf merge(ObjectLeaf... leaves)
```

#### Examples

**Basic Merge**

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class MergeExample {
    
    void basicMerge() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf user = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "John")
                .build();

        ObjectLeaf details = ObjectLeafBuilder.builder()
                .put("age", 30)
                .put("city", "New York")
                .build();

        ObjectLeaf result = operator.merge(user, details);
    }
}
```

**Merging with Nested Objects**

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class NestedMergeExample {
    
    void mergeWithNestedObjects() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf address = ObjectLeafBuilder.builder()
                .put("street", "123 Main St")
                .put("city", "Boston")
                .build();

        ObjectLeaf user = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("address", address)
                .build();

        ObjectLeaf profile = ObjectLeafBuilder.builder()
                .put("name", "Alice")
                .put("active", true)
                .build();

        ObjectLeaf result = operator.merge(user, profile);
    }
}
```

**Handling Duplicate Keys**

When merging objects with duplicate keys, the last value overwrites previous values:

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class DuplicateKeysExample {
    
    void handleDuplicates() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("status", "pending")
                .put("id", 1)
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("status", "approved")
                .build();

        ObjectLeaf result = operator.merge(leaf1, leaf2);
    }
}
```

**Merging Multiple Objects**

You can merge more than two objects in a single operation:

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class MultipleMergeExample {
    
    void mergeMultiple() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf product = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "Product A")
                .build();

        ObjectLeaf pricing = ObjectLeafBuilder.builder()
                .put("price", 29.99)
                .put("inStock", true)
                .build();

        ObjectLeaf category = ObjectLeafBuilder.builder()
                .put("category", "Electronics")
                .put("quantity", 100)
                .build();

        ObjectLeaf result = operator.merge(product, pricing, category);
    }
}
```

### Extracting String Values

The `extractStringValue` method retrieves the string value from a `JSONLeaf` if it represents a string type.

#### Usage

```java
Optional<String> extractStringValue(JSONLeaf leaf)
```

#### Example

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExtractStringExample {
    
    void extractString() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf user = ObjectLeafBuilder.builder()
                .put("name", "John Doe")
                .put("email", "john@example.com")
                .build();

        var nameLeaf = user.asLeaf("name");
        if (nameLeaf.isPresent()) {
            var name = operator.extractStringValue(nameLeaf.get());
            name.ifPresent(value -> System.out.println("Name: " + value));
        }
    }
}
```

### Extracting Boolean Values

The `extractBooleanValue` method retrieves the boolean value from a `JSONLeaf` if it represents a boolean type.

#### Usage

```java
Optional<Boolean> extractBooleanValue(JSONLeaf leaf)
```

#### Example

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExtractBooleanExample {
    
    void extractBoolean() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf user = ObjectLeafBuilder.builder()
                .put("isActive", true)
                .put("verified", false)
                .build();

        var activeLeaf = user.asLeaf("isActive");
        if (activeLeaf.isPresent()) {
            var isActive = operator.extractBooleanValue(activeLeaf.get());
            isActive.ifPresent(value -> {
                if (value) {
                    System.out.println("User is active");
                }
            });
        }
    }
}
```

### Extracting Numeric Values

The `extractLongValue` method retrieves numeric values as `Double` from a `JSONLeaf` if it represents a number type.

#### Usage

```java
Optional<Double> extractLongValue(JSONLeaf leaf)
```

#### Example

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExtractNumberExample {
    
    void extractNumber() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf product = ObjectLeafBuilder.builder()
                .put("price", 99.99)
                .put("quantity", 42)
                .build();

        var priceLeaf = product.asLeaf("price");
        if (priceLeaf.isPresent()) {
            var price = operator.extractLongValue(priceLeaf.get());
            price.ifPresent(value -> System.out.println("Price: $" + value));
        }

        var quantityLeaf = product.asLeaf("quantity");
        if (quantityLeaf.isPresent()) {
            var quantity = operator.extractLongValue(quantityLeaf.get());
            quantity.ifPresent(value -> System.out.println("Quantity: " + value.intValue()));
        }
    }
}
```

### Complete Example

Here is a complete example demonstrating multiple operations:

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class CompleteExample {
    
    void processUserData() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf basicInfo = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "Alice Smith")
                .build();

        ObjectLeaf contactInfo = ObjectLeafBuilder.builder()
                .put("email", "alice@example.com")
                .put("verified", true)
                .build();

        ObjectLeaf stats = ObjectLeafBuilder.builder()
                .put("loginCount", 150)
                .put("lastLoginDays", 2.5)
                .build();

        ObjectLeaf merged = operator.merge(basicInfo, contactInfo, stats);

        var nameLeaf = merged.asLeaf("name");
        nameLeaf.ifPresent(leaf -> {
            operator.extractStringValue(leaf)
                    .ifPresent(name -> System.out.println("User: " + name));
        });

        var verifiedLeaf = merged.asLeaf("verified");
        verifiedLeaf.ifPresent(leaf -> {
            operator.extractBooleanValue(leaf)
                    .ifPresent(verified -> {
                        if (verified) {
                            System.out.println("Account is verified");
                        }
                    });
        });

        var loginCountLeaf = merged.asLeaf("loginCount");
        loginCountLeaf.ifPresent(leaf -> {
            operator.extractLongValue(leaf)
                    .ifPresent(count -> System.out.println("Login count: " + count.intValue()));
        });
    }
}
```

---

## Spanish

### Fusión de Hojas de Objetos

El método `merge` combina múltiples instancias de `ObjectLeaf` en una sola `ObjectLeaf`. Cuando existen claves duplicadas, el último valor tiene precedencia.

#### Uso

```java
ObjectLeaf merge(ObjectLeaf... leaves)
```

#### Ejemplos

**Fusión Básica**

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class EjemploFusion {
    
    void fusionBasica() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf usuario = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("nombre", "Juan")
                .build();

        ObjectLeaf detalles = ObjectLeafBuilder.builder()
                .put("edad", 30)
                .put("ciudad", "Nueva York")
                .build();

        ObjectLeaf resultado = operator.merge(usuario, detalles);
    }
}
```

**Fusión con Objetos Anidados**

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class EjemploFusionAnidada {
    
    void fusionarConObjetosAnidados() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf direccion = ObjectLeafBuilder.builder()
                .put("calle", "Av. Principal 123")
                .put("ciudad", "Boston")
                .build();

        ObjectLeaf usuario = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("direccion", direccion)
                .build();

        ObjectLeaf perfil = ObjectLeafBuilder.builder()
                .put("nombre", "Alicia")
                .put("activo", true)
                .build();

        ObjectLeaf resultado = operator.merge(usuario, perfil);
    }
}
```

**Manejo de Claves Duplicadas**

Al fusionar objetos con claves duplicadas, el último valor sobrescribe los valores anteriores:

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class EjemploClaveDuplicada {
    
    void manejarDuplicados() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf hoja1 = ObjectLeafBuilder.builder()
                .put("estado", "pendiente")
                .put("id", 1)
                .build();

        ObjectLeaf hoja2 = ObjectLeafBuilder.builder()
                .put("estado", "aprobado")
                .build();

        ObjectLeaf resultado = operator.merge(hoja1, hoja2);
    }
}
```

**Fusión de Múltiples Objetos**

Se pueden fusionar más de dos objetos en una sola operación:

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class EjemploFusionMultiple {
    
    void fusionarMultiples() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf producto = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("nombre", "Producto A")
                .build();

        ObjectLeaf precios = ObjectLeafBuilder.builder()
                .put("precio", 29.99)
                .put("enStock", true)
                .build();

        ObjectLeaf categoria = ObjectLeafBuilder.builder()
                .put("categoria", "Electrónica")
                .put("cantidad", 100)
                .build();

        ObjectLeaf resultado = operator.merge(producto, precios, categoria);
    }
}
```

### Extracción de Valores de Cadena

El método `extractStringValue` recupera el valor de cadena de un `JSONLeaf` si representa un tipo de cadena.

#### Uso

```java
Optional<String> extractStringValue(JSONLeaf leaf)
```

#### Ejemplo

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class EjemploExtraerCadena {
    
    void extraerCadena() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf usuario = ObjectLeafBuilder.builder()
                .put("nombre", "Juan Pérez")
                .put("email", "juan@ejemplo.com")
                .build();

        var hojaNombre = usuario.asLeaf("nombre");
        if (hojaNombre.isPresent()) {
            var nombre = operator.extractStringValue(hojaNombre.get());
            nombre.ifPresent(valor -> System.out.println("Nombre: " + valor));
        }
    }
}
```

### Extracción de Valores Booleanos

El método `extractBooleanValue` recupera el valor booleano de un `JSONLeaf` si representa un tipo booleano.

#### Uso

```java
Optional<Boolean> extractBooleanValue(JSONLeaf leaf)
```

#### Ejemplo

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class EjemploExtraerBooleano {
    
    void extraerBooleano() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf usuario = ObjectLeafBuilder.builder()
                .put("estaActivo", true)
                .put("verificado", false)
                .build();

        var hojaActivo = usuario.asLeaf("estaActivo");
        if (hojaActivo.isPresent()) {
            var estaActivo = operator.extractBooleanValue(hojaActivo.get());
            estaActivo.ifPresent(valor -> {
                if (valor) {
                    System.out.println("El usuario está activo");
                }
            });
        }
    }
}
```

### Extracción de Valores Numéricos

El método `extractLongValue` recupera valores numéricos como `Double` de un `JSONLeaf` si representa un tipo numérico.

#### Uso

```java
Optional<Double> extractLongValue(JSONLeaf leaf)
```

#### Ejemplo

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class EjemploExtraerNumero {
    
    void extraerNumero() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf producto = ObjectLeafBuilder.builder()
                .put("precio", 99.99)
                .put("cantidad", 42)
                .build();

        var hojaPrecio = producto.asLeaf("precio");
        if (hojaPrecio.isPresent()) {
            var precio = operator.extractLongValue(hojaPrecio.get());
            precio.ifPresent(valor -> System.out.println("Precio: $" + valor));
        }

        var hojaCantidad = producto.asLeaf("cantidad");
        if (hojaCantidad.isPresent()) {
            var cantidad = operator.extractLongValue(hojaCantidad.get());
            cantidad.ifPresent(valor -> System.out.println("Cantidad: " + valor.intValue()));
        }
    }
}
```

### Ejemplo Completo

Aquí hay un ejemplo completo que demuestra múltiples operaciones:

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class EjemploCompleto {
    
    void procesarDatosUsuario() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf infoBasica = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("nombre", "Alicia García")
                .build();

        ObjectLeaf infoContacto = ObjectLeafBuilder.builder()
                .put("email", "alicia@ejemplo.com")
                .put("verificado", true)
                .build();

        ObjectLeaf estadisticas = ObjectLeafBuilder.builder()
                .put("contadorIngresos", 150)
                .put("ultimoIngresoDias", 2.5)
                .build();

        ObjectLeaf fusionado = operator.merge(infoBasica, infoContacto, estadisticas);

        var hojaNombre = fusionado.asLeaf("nombre");
        hojaNombre.ifPresent(hoja -> {
            operator.extractStringValue(hoja)
                    .ifPresent(nombre -> System.out.println("Usuario: " + nombre));
        });

        var hojaVerificado = fusionado.asLeaf("verificado");
        hojaVerificado.ifPresent(hoja -> {
            operator.extractBooleanValue(hoja)
                    .ifPresent(verificado -> {
                        if (verificado) {
                            System.out.println("La cuenta está verificada");
                        }
                    });
        });

        var hojaContador = fusionado.asLeaf("contadorIngresos");
        hojaContador.ifPresent(hoja -> {
            operator.extractLongValue(hoja)
                    .ifPresent(contador -> System.out.println("Ingresos: " + contador.intValue()));
        });
    }
}
```

---

## French

### Fusion de Feuilles d'Objets

La méthode `merge` combine plusieurs instances d'`ObjectLeaf` en une seule `ObjectLeaf`. Lorsque des clés en double existent, la dernière valeur a la priorité.

#### Utilisation

```java
ObjectLeaf merge(ObjectLeaf... leaves)
```

#### Exemples

**Fusion de Base**

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExempleFusion {
    
    void fusionDeBase() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf utilisateur = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("nom", "Jean")
                .build();

        ObjectLeaf details = ObjectLeafBuilder.builder()
                .put("age", 30)
                .put("ville", "New York")
                .build();

        ObjectLeaf resultat = operator.merge(utilisateur, details);
    }
}
```

**Fusion avec Objets Imbriqués**

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExempleFusionImbriquee {
    
    void fusionnerAvecObjetsImbriques() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf adresse = ObjectLeafBuilder.builder()
                .put("rue", "123 Rue Principale")
                .put("ville", "Boston")
                .build();

        ObjectLeaf utilisateur = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("adresse", adresse)
                .build();

        ObjectLeaf profil = ObjectLeafBuilder.builder()
                .put("nom", "Alice")
                .put("actif", true)
                .build();

        ObjectLeaf resultat = operator.merge(utilisateur, profil);
    }
}
```

**Gestion des Clés en Double**

Lors de la fusion d'objets avec des clés en double, la dernière valeur écrase les valeurs précédentes:

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExempleCleDupliquee {
    
    void gererDoublons() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf feuille1 = ObjectLeafBuilder.builder()
                .put("statut", "en attente")
                .put("id", 1)
                .build();

        ObjectLeaf feuille2 = ObjectLeafBuilder.builder()
                .put("statut", "approuvé")
                .build();

        ObjectLeaf resultat = operator.merge(feuille1, feuille2);
    }
}
```

**Fusion de Plusieurs Objets**

Vous pouvez fusionner plus de deux objets en une seule opération:

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExempleFusionMultiple {
    
    void fusionnerPlusieurs() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf produit = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("nom", "Produit A")
                .build();

        ObjectLeaf tarification = ObjectLeafBuilder.builder()
                .put("prix", 29.99)
                .put("enStock", true)
                .build();

        ObjectLeaf categorie = ObjectLeafBuilder.builder()
                .put("categorie", "Électronique")
                .put("quantite", 100)
                .build();

        ObjectLeaf resultat = operator.merge(produit, tarification, categorie);
    }
}
```

### Extraction de Valeurs de Chaîne

La méthode `extractStringValue` récupère la valeur de chaîne d'un `JSONLeaf` s'il représente un type de chaîne.

#### Utilisation

```java
Optional<String> extractStringValue(JSONLeaf leaf)
```

#### Exemple

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExempleExtraireChaîne {
    
    void extraireChaîne() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf utilisateur = ObjectLeafBuilder.builder()
                .put("nom", "Jean Dupont")
                .put("email", "jean@exemple.com")
                .build();

        var feuilleNom = utilisateur.asLeaf("nom");
        if (feuilleNom.isPresent()) {
            var nom = operator.extractStringValue(feuilleNom.get());
            nom.ifPresent(valeur -> System.out.println("Nom: " + valeur));
        }
    }
}
```

### Extraction de Valeurs Booléennes

La méthode `extractBooleanValue` récupère la valeur booléenne d'un `JSONLeaf` s'il représente un type booléen.

#### Utilisation

```java
Optional<Boolean> extractBooleanValue(JSONLeaf leaf)
```

#### Exemple

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExempleExtraireBooleén {
    
    void extraireBooleen() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf utilisateur = ObjectLeafBuilder.builder()
                .put("estActif", true)
                .put("verifie", false)
                .build();

        var feuilleActif = utilisateur.asLeaf("estActif");
        if (feuilleActif.isPresent()) {
            var estActif = operator.extractBooleanValue(feuilleActif.get());
            estActif.ifPresent(valeur -> {
                if (valeur) {
                    System.out.println("L'utilisateur est actif");
                }
            });
        }
    }
}
```

### Extraction de Valeurs Numériques

La méthode `extractLongValue` récupère les valeurs numériques sous forme de `Double` d'un `JSONLeaf` s'il représente un type numérique.

#### Utilisation

```java
Optional<Double> extractLongValue(JSONLeaf leaf)
```

#### Exemple

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExempleExtraireNombre {
    
    void extraireNombre() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf produit = ObjectLeafBuilder.builder()
                .put("prix", 99.99)
                .put("quantite", 42)
                .build();

        var feuillePrix = produit.asLeaf("prix");
        if (feuillePrix.isPresent()) {
            var prix = operator.extractLongValue(feuillePrix.get());
            prix.ifPresent(valeur -> System.out.println("Prix: $" + valeur));
        }

        var feuilleQuantite = produit.asLeaf("quantite");
        if (feuilleQuantite.isPresent()) {
            var quantite = operator.extractLongValue(feuilleQuantite.get());
            quantite.ifPresent(valeur -> System.out.println("Quantité: " + valeur.intValue()));
        }
    }
}
```

### Exemple Complet

Voici un exemple complet démontrant plusieurs opérations:

```java
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.operations.ObjectLeafOperator;

class ExempleComplet {
    
    void traiterDonneesUtilisateur() {
        ObjectLeafOperator operator = new ObjectLeafOperator();
        
        ObjectLeaf infoBase = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("nom", "Alice Martin")
                .build();

        ObjectLeaf infoContact = ObjectLeafBuilder.builder()
                .put("email", "alice@exemple.com")
                .put("verifie", true)
                .build();

        ObjectLeaf statistiques = ObjectLeafBuilder.builder()
                .put("compteurConnexions", 150)
                .put("derniereConnexionJours", 2.5)
                .build();

        ObjectLeaf fusionne = operator.merge(infoBase, infoContact, statistiques);

        var feuilleNom = fusionne.asLeaf("nom");
        feuilleNom.ifPresent(feuille -> {
            operator.extractStringValue(feuille)
                    .ifPresent(nom -> System.out.println("Utilisateur: " + nom));
        });

        var feuilleVerifie = fusionne.asLeaf("verifie");
        feuilleVerifie.ifPresent(feuille -> {
            operator.extractBooleanValue(feuille)
                    .ifPresent(verifie -> {
                        if (verifie) {
                            System.out.println("Le compte est vérifié");
                        }
                    });
        });

        var feuilleCompteur = fusionne.asLeaf("compteurConnexions");
        feuilleCompteur.ifPresent(feuille -> {
            operator.extractLongValue(feuille)
                    .ifPresent(compteur -> System.out.println("Connexions: " + compteur.intValue()));
        });
    }
}
```

