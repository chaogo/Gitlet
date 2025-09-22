# Gitlet Design Document

## Classes

- **Blob**:represents file contents snapshot, stores id (hash) and content.
- **Tree**:represents directory snapshot, simplified flat map (filename → blobId).
- **Commit**:represents commit object with id, parent commit, tree id, message, and timestamp.
- **Index**:staging area (map of file path → blob id).
- **Repository**:controller class, manages .gitlet structure, implements commands like init, add, commit, checkout, etc.
- **Utils**:helper methods (hashing, serialization, file IO).
- **Main**:CLI entry point, parses user commands and delegates to Repository.

## .gitlet directory

```
.gitlet/
├── HEAD          # a plain text file that says what branch you are on, e.g., e.g. ref: refs/heads/master
├── index         # a binary file of the list of file paths pointing to blob objects, e.g., hello.txt -> e965407...
├── objects/      # database for all gitlet objects: blob, tree, commit, dividing into subfolders based on the SHA-1 hash prefix (first 2 hex digits)
├── refs/         # plain text files containing commit hash
|    └── heads/   # the latest commit hash for each local branch (branch tips), e.g., master, feature-x, etc.
│  
└── logs/
    ├── HEAD      # history of what HEAD had pointed at over time
    └── refs/     # history for each brach
```


## Gitlet commands and algorithms
- `java gitlet.Main init`
- `java gitlet.Main add [file name]`
- `java gitlet.Main commit [message]`
- `java gitlet.Main rm [file name]`
- `java gitlet.Main log`
- `java gitlet.Main global-log`
- `java gitlet.Main find [commit message]`
- `java gitlet.Main status`
- `java gitlet.Main checkout -- [file name]`
- `java gitlet.Main checkout [commit id] -- [file name]`
- `java gitlet.Main checkout [branch name]`
- `java gitlet.Main branch [branch name]`
- `java gitlet.Main rm-branch [branch name]`
- `java gitlet.Main reset [commit id]`
- `java gitlet.Main merge [branch name]`


## Comparing to Git
- In index, a file path points to other metadata as well in git, but gets ignored in getlet
- In Tree object, a directory tree is simplified as a list in gitlet, i.e., it's always a flat directory without subdirectory
- In objects, Git "packs" objects when the repo grows big. 