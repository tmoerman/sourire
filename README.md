# sourire

> "D'un enfant le sourire - Agrandit l'univers" -- Charles de Leusse

In the grand tradition of Clojure libraries we begin with an irrelevant
quote.

Sourire is a minimal web API on top of the [Smiles](http://en.wikipedia.org/wiki/Simplified_molecular-input_line-entry_system) 
molecule rendering function of the [GGA Software](https://github.com/ggasoftware) [Indigo](https://github.com/ggasoftware/indigo) 
cheminformatics toolkit.

Usage:

 [base-url]/molecule/[url-encoded-smiles-string]?indigo-param-name=param-value
 
Example:
 
 http://localhost:8000/molecule/OCCc1c%28C%29%5Bn%2B%5D%28%3Dcs1%29Cc2cnc%28C%29nc%28N%292?render-comment=Vitamin%20B1
 
 ![vitamin b1](img/vit_b1.png)
 
 
## REPL

 TODO
 
## STANDALONE

 TODO
