/**
 * Created by snadi on 2018-03-04.
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.repodriller.domain.Commit;
import org.repodriller.filter.commit.OnlyModificationsWithFileTypes;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

import java.util.Arrays;

public class FullCommitVisitor implements CommitVisitor {

    @Override
    public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {

        OnlyModificationsWithFileTypes filter = new OnlyModificationsWithFileTypes(Arrays.asList("java"));


        writer.write(
                commit.getHash(),
                commit.getMsg(),
                commit.getDate().getTime(),
                commit.getModifications(),
                (filter.accept(commit) ? "true" : "false"),
                commit.getCommitter().getEmail(),
                commit.getCommitter().getName()
        );

    }
}