# The Final Codedown

## Kubernetes

Requirements : 

- [Kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
- [Kind](https://github.com/kubernetes-sigs/kind)

Don't forget to launch by using `sh kind.sh` !

To run :

```bash
sh prepare.sh
sh krun-base.sh
sh kdatabase-restore.sh
sh krun-hybrid.sh
sh kport.sh
```

To test :

```bash
cd tfc-tdd
sh prepare.sh
sh run.sh hybrid
```