# Gitlet Design Document
_might evolve over time_

## Classes
- Blob
- Tree
- Commit

- Repository
- Main
- Utils


## .gitlet directory

```
.gitlet/
├── HEAD -> a plain text file that says what branch you are on, e.g., e.g. ref: refs/heads/master
├── index -> a binary file of the list of file paths pointing to blob objects, e.g., hello.txt -> e965407...
├── objects/ -> database for all gitlet objects: blob, tree, commit
├── refs/
│   ├── heads/
│   ├── tags/
│   └── remotes/
│       └── origin/
└── logs/
    ├── HEAD
    └── refs/
```


## Algorithm


## Comparing to Git
- In index, a file path points to other metadata as well in git, but gets ignored in getlet
- In Tree object, a directory tree is simplified as a list in gitlet, i.e., it's always a flat directory without subdirectory
- In objects, Git "packs" objects when the repo grows big. 