import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class SearchEngine {

	/*Populate a BST from a file
	 * @param searchTree - BST to be populated
	 * @param fileName - name of the input file
	 * @returns false if file not found, true otherwise
	 */
	public static boolean populateSearchTree(BSTree<String> searchTree, String fileName) {
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				//read two lines - one for document and the next line for the list of keywords
				String document = scanner.nextLine().trim();
				String keywords[] = scanner.nextLine().split(" ");

				for(String keyword : keywords){
                    //check if a keyword exists in the BST
                    keyword = keyword.toLowerCase();
                    if(searchTree.findKey(keyword)){
                        //add document to datalist of node with keyword as key
                        if(!searchTree.findDataList(keyword).contains(document)){
                            searchTree.insertData(keyword, document);
                        }
                    }else{
                        //add node to BST with keyword as key
                        searchTree.insert(keyword);
                        //add document to dataList of node
                        searchTree.insertData(keyword, document);
                    }

                }

				
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			System.out.println("\nFile not found!!");
			return false;
		}
		return true;
	}
	
	/*Search a query in a BST
	 * @param searchTree - BST to be searched
	 * @param fileName - query string
	 * @returns LinkedList of documents in which the query string is found
	 */
	public static void searchMyQuery(BSTree<String> searchTree, String query) {
	    String[] queryWords = query.toLowerCase().split(" ");
        LinkedList<String> printedDocs = new LinkedList<>();
        LinkedList<String> intersection = new LinkedList<>();
	    if(queryWords.length > 1){
            try{
                intersection.addAll(searchTree.findDataList(queryWords[0]));
                for(int i = 1; i < queryWords.length; i++){
                    if(!searchTree.findKey(queryWords[i]) || searchTree.findDataList(queryWords[i]) == null) {
                        print(query, null);
                        break;
                    }else if(intersection.isEmpty()){
                        intersection = searchTree.findDataList(queryWords[i]);
                    }else{
                        LinkedList<String> documents;
                        documents = searchTree.findDataList(queryWords[i]);
                        intersection.retainAll(documents);
                    }
                }
            }catch (IllegalArgumentException e){
                intersection.clear();
            }
            print(query, intersection);
            if(!intersection.isEmpty()){
                printedDocs.addAll(intersection);
            }
        }
        for(String keyword: queryWords){
            LinkedList<String> documents;
            LinkedList<String> relatedDocs = new LinkedList<>();
            try{
                documents = searchTree.findDataList(keyword);
                if(printedDocs.containsAll(documents)){
                    continue;
                }
                for(String document : documents){
                    if(!printedDocs.contains(document)){
                        relatedDocs.add(document);
                    }
                }
            }catch(IllegalArgumentException e){
                relatedDocs.clear();
            }
            print(keyword, relatedDocs);
            if(!relatedDocs.isEmpty()){
                printedDocs.addAll(relatedDocs);
            }
        }

	}
	
	/*Print method 
	 * @param query input
	 * @param documents - result of SearchMyQuery
	 */
	 
	public static void print(String query, LinkedList<String> documents) {
		if(documents==null || documents.isEmpty())
			System.out.println("The search yielded no results for "+query);
		else {
			Object[] converted = documents.toArray();
			Arrays.sort(converted);
			System.out.println("Documents related to "+ query +" are: "+Arrays.toString(converted));
		}
	}
	
	public static void main( String[] args ) {
		
		if(args.length < 2) {
			System.err.println("Invalid number of arguments passed");
			return;
		}
		
		BSTree<String> searchTree = new BSTree<>();
		
		String fileName = args[0];
		String query = args[1];
		
		//Create my BST from file
		boolean check = populateSearchTree(searchTree, fileName);
		if(check == false) {
			System.out.println("\nUnable to create search tree");
		}
		
		searchMyQuery(searchTree, query);
	}
}
