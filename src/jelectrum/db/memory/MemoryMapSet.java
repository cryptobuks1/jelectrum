package jelectrum.db.memory;

import jelectrum.db.DBMapSet; 


import org.bitcoinj.core.Sha256Hash;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Set;
import jelectrum.db.DBTooManyResultsException;


public class MemoryMapSet extends DBMapSet
{
  private TreeMap<String, HashSet<Sha256Hash>> m=new TreeMap<>();

  public synchronized void add(String key, Sha256Hash hash)
  {
    if (!m.containsKey(key)) m.put(key, new HashSet<Sha256Hash>());

    m.get(key).add(hash);

  }
  public synchronized Set<Sha256Hash> getSet(String key, int max_results)
  {
    HashSet<Sha256Hash> ret = new HashSet<Sha256Hash>();
  
    
    if (m.containsKey(key))
    {
      ret.addAll(m.get(key));
    }
    if (ret.size() > max_results) throw new DBTooManyResultsException();
    return ret;
  }

}


