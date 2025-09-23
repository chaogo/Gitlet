package gitlet;

import java.io.IOException;
import java.io.Serializable;
import java.io.File;
import java.util.Date;

import static gitlet.Utils.*;


/** Represents a gitlet commit object.
 *  Includes methods to save and read
 *
 *  @author Chao
 */
public class Commit implements Serializable {

    /** The object directory where commits are saved */
    public static final File OBJECT_DIR = join(CWD, ".gitlet/objects");

    /** The SHA-1 hash of this commit, i.e., commit id */
    private String id;

    /** The parent commit id, null if this is the root commit */
    private String parent;

    /** The tree id, snapshot of files for this commit */
    private String treeId;

    /** The message of this Commit */
    private String message;

    /** The timestamp of this commit */
    private Date timestamp;

    public Commit(String parent, String treeId, String message) {
        this.parent = parent;
        this.treeId = treeId;
        this.message = message;
        this.timestamp = new Date(); // current time by default
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getParent() {
        return parent;
    }

    public String getTreeId() {
        return treeId;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Setter for id is necessary because the commit's id is set after it's been serialized
     * @param id: Sha-1(serialize(commitWithoutId))
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Save this commit to .gitlet/objects/[hash prefix]/
     */
    public void save() throws IOException {
        String dirName = id.substring(0, 2);
        File commitFolder = join(OBJECT_DIR, dirName);

        if (!commitFolder.exists()) {
            commitFolder.mkdir();
        }

        String fileName = id.substring(2);
        File commitFile = Utils.join(commitFolder, fileName);
        commitFile.createNewFile();
        writeObject(commitFile, this);
    }

    /**
     * Read a commit
     * @param id: commit id
     * @return commit: read from the file under .gitlet/objects/
     */
    public static Commit read(String id) {
        String dirName = id.substring(0, 2);
        String fileName = id.substring(2);
        File commit = Utils.join(OBJECT_DIR, dirName, fileName);
        return readObject(commit, Commit.class);
    }
}
