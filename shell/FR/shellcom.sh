#! /bin/bash  

IFS=$'\t'
while read RegionCode RegionName DeptCode ArrondissementCode CantonCode CommuneCode CommuneName PopulationMunicipale PopulationOther PopulationTotal  
do 
    echo $DeptCode$CommuneCode ,    $DeptCode,  $CommuneCode,  $CommuneName, $PopulationTotal
mysql --user=root --password=rabah123 rdfdatabase <<EOF
   
insert into inseepop values ('$DeptCode$CommuneCode','$DeptCode','$CommuneCode','$CommuneName','$PopulationTotal');

EOF
done <$1 2>error.txt



		
