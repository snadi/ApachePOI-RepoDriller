/**
 * Created by snadi on 2018-03-04.
 */
import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

import java.util.ArrayList;

public class ApachePOIStudy implements Study {

    public static void main(String[] args) {
        new RepoDriller().start(new ApachePOIStudy());
    }

    @Override
    public void execute() {
        //first commit hash: 0805dbbe510bfb860fb11cf022325d34c130fb43
        new RepositoryMining()
        .in(GitRepository.singleProject("/Users/snadi/Documents/Academic/Projects/DysDoc/poi"))
        .through(Commits.range("ce7446c76ac86b9abb062bbfc738efb765923232", "219dff00e61e9ad73a16b73a430cb80954f6262c"))
        .process(new FullCommitVisitor(), new CSVFile("devs.csv", false, new String[]{"CommitHash", "CommitMsg", "CommitDate", "CommitFileModifications", "ModifiesJavaFile?", "CommitterEmail", "CommitterName"}))
        .mine();
    }
}
