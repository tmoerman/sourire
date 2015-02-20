# Sourire

> "D'un enfant le sourire - Agrandit l'univers" -- Charles de Leusse

In the grand tradition of Clojure libraries we begin with an irrelevant
quote.

Sourire is a minimal web API on top of the [Smiles](http://en.wikipedia.org/wiki/Simplified_molecular-input_line-entry_system) 
molecule rendering function of the [GGA Software](https://github.com/ggasoftware) [Indigo](https://github.com/ggasoftware/indigo) 
cheminformatics toolkit.

## Usage

`[base-url]/molecule/[url-encoded-smiles-string]?indigo-param-name=param-value`

Smiles notation uses characters that are considered unsafe for use in URLs. Therefore
the API only accepts smiles strings in [URL encoded](http://www.w3schools.com/tags/ref_urlencode.asp) format.
   
## Run

Use [leiningen](http://leiningen.org/) to create a runnable jar (assuming you are in the project folder):

`$ lein create-standalone`

Launch the application:

`java -jar target/sourire-0.1.0-SNAPSHOT-standalone.jar`

You can provide a Java argument to specify the web server port:

`java -jar target/sourire-0.1.0-SNAPSHOT-standalone.jar :port 8080`

## REPL 

You can run a Clojure REPL supporting the famous Stuart Sierre [Workflow, Reloaded](http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded) pattern.

```bash
$ lein user-repl
WARNING: user-level profile defined in project files.
nREPL server started on port 8777 on host 127.0.0.1
REPL-y 0.3.0
Clojure 1.7.0-alpha5
    Docs: (doc function-name-here)
          (find-doc "part-of-name-here")
  Source: (source function-name-here)
 Javadoc: (javadoc java-object-or-class-here)
    Exit: Control+D or (exit) or (quit)
 Results: Stored in vars *1, *2, *3, an exception in *e

user=> (launch)
```

## Examples

##### Vitamin B1

Smiles code: `OCCc1c(C)[n+](=cs1)Cc2cnc(C)nc(N)2`
 
Sourire URL: `http://localhost:8000/molecule/OCCc1c%28C%29%5Bn%2B%5D%28%3Dcs1%29Cc2cnc%28C%29nc%28N%292?render-comment=Vitamin%20B1`
 
![vitamin b1](img/vit_b1.png)
 
##### Glucose

Smiles code: `OC[C@@H](O1)[C@@H](O)[C@H](O)[C@@H](O)[C@@H](O)1`

Sourire URL: `http://localhost:8000/molecule/OC%5BC%40%40H%5D%28O1%29%5BC%40%40H%5D%28O%29%5BC%40H%5D%28O%29%5BC%40%40H%5D%28O%29%5BC%40%40H%5D%28O%291?render-comment=glucose`
 
![Glucose](img/glucose.png)
  
##### Caffeine

Smiles code: `CN1C=NC2=C1C(=O)N(C)C(=O)N2C`

Sourire URL: `http://localhost:8000/molecule/CN1C%3DNC2%3DC1C%28%3DO%29N%28C%29C%28%3DO%29N2C?render-comment=caffeine`
 
![Caffeine](img/caffeine.png)

##### Oenanthotoxin

Smiles code: `CCC[C@@H](O)CC\\C=C\\C=C\\C#CC#C\\C=C\\CO`

Sourire URL: `http://localhost:8000/molecule/CCC%5BC%40%40H%5D%28O%29CC%5CC%3DC%5CC%3DC%5CC%23CC%23C%5CC%3DC%5CCO?render-comment=oenanthotoxin`

![Caffeine](img/oenanthotoxin.png)

## License

The Do Whatever The Hell You Like License. 

I am not liable for accidentally launched intercontinental ballistic missionaries.
