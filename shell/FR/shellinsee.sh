#! /bin/bash  

IFS=$'\t'
while read RegionCode RegionName DeptCode ArrondissementCode CantonCode CommuneCode CommuneName PopulationMunicipale PopulationOther PopulationTotal  
do 
    echo $RegionCode $RegionName $DeptCode $ArrondissementCode $CantonCode $CommuneCode $CommuneName $PopulationMunicipale $PopulationOther $PopulationTotal  
mysql --user=root --password=rabah123 rdfdatabase <<EOF
   
insert into inseepop values ($DeptCode,$CommuneCode,'$RegionName','$CommuneName','$PopulationMunicipale','$PopulationOther','$PopulationTotal',$RegionCode,$ArrondissementCode,$CantonCode);

EOF
done <$1 2>error.txt



		
