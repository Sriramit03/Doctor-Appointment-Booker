import { View, Text, FlatList, TouchableOpacity, Alert } from "react-native";
import { useEffect, useState } from "react";
import { format } from "date-fns";
import CustomHeader from "@/components/CustomHeader";
import { SafeAreaView } from "react-native-safe-area-context";
import { useGlobalContext } from "@/context/GlobalProvider";
import axios from "axios";
import { router } from "expo-router";

// Mock data for appointments
const mockAppointments = [
  {
    id: "1",
    doctorName: "Dr. Sarah Johnson",
    specialty: "Cardiologist",
    date: new Date("2024-02-20T10:30:00"),
    status: "upcoming",
  },
  {
    id: "2",
    doctorName: "Dr. Michael Chen",
    specialty: "Neurologist",
    date: new Date("2024-02-15T14:00:00"),
    status: "completed",
  },
  {
    id: "3",
    doctorName: "Dr. Emily Williams",
    specialty: "Pediatrician",
    date: new Date("2024-02-10T11:00:00"),
    status: "completed",
  },
];

function AppointmentCard({ appointment, onReschedule, onCancel }) {
  const isUpcoming = appointment.status === "PENDING";

  return (
    <View className="bg-secondaryBlack rounded-xl p-4 mb-4 shadow">
      <View className="mb-4">
        <Text className="text-lg font-semibold text-primaryLight mb-1">
          {appointment.doctor.name}
        </Text>
        <Text className="text-sm text-slate-400 mb-2 font-pregular">
          {appointment.doctor.specialization}
        </Text>
        <Text className="text-sm text-slate-400 mb-2 font-pregular">
          {
            /* format(new Date(appointment.date), "PPP") */ appointment.appointmentDate
          }{" "}
          at {/* format(appointment.date, "p") */ appointment.appointmentTime}
        </Text>
        <Text className="text-sm text-slate-200 mb-2 font-pregular">
          {appointment.complains}
        </Text>
        <Text
          className={`text-sm font-medium ${
            isUpcoming ? "text-blue-600" : "text-emerald-600"
          }`}
        >
          {appointment.status.charAt(0).toUpperCase() +
            appointment.status.slice(1)}
        </Text>
      </View>

      {isUpcoming && (
        <View className="flex-row gap-3">
          <TouchableOpacity
            className="bg-blue-600 px-4 py-2 rounded-lg flex-row items-center justify-center"
            onPress={() =>
              onReschedule(appointment.appointmentId, appointment.doctorId)
            }
          >
            <Text className="text-white text-sm font-medium">Reschedule</Text>
          </TouchableOpacity>
          <TouchableOpacity
            className="bg-red-50 px-4 py-2 rounded-lg flex-row items-center justify-center"
            onPress={() => onCancel(appointment.appointmentId)}
          >
            <Text className="text-red-600 text-sm font-medium">Cancel</Text>
          </TouchableOpacity>
        </View>
      )}
    </View>
  );
}

export default function Appointments() {
  const { user } = useGlobalContext();
  const [appointments, setAppointments] = useState([]);
  const [doctors, setDoctors] = useState();

  useEffect(() => {
    getAppointments();
  }, []);

  const getAppointments = async () => {
    if (user.id != null) {
      try {
        const response = await axios.get(
          `http://192.168.43.29:8080/appointments/patient/${user.id}`
        );
        if (response.status == 200) setAppointments(response.data);
        else Alert.alert("Error ", "Error in fetching data");
      } catch (error) {
        Alert.alert("Exception Occurred", error.toString());
      }
    }
  };

  const handleCancel = async (appointmentId) => {
    console.log("Inside On cancel", appointmentId);
    if (appointmentId) {
      try {
        const response = await axios.delete(
          `http://192.168.43.29:8080/appointments/${appointmentId}`
        );
        if (response.status == 200) {
          Alert.alert("Successful", "Appointment Deleted Successfully");
          await getAppointments();
        }
      } catch (err) {
        Alert.alert("Error", err.toString());
      }
    }
  };

  const handleReschedule = (appointmentId, doctorId) => {
    router.push(`/booking/${doctorId}?appointmentId=${appointmentId}`);
  };

  return (
    <SafeAreaView className="flex-1 bg-primaryBlack">
      <CustomHeader title={"My Appointments"} />
      <FlatList
        data={appointments}
        renderItem={({ item }) => (
          <AppointmentCard
            appointment={item}
            onReschedule={handleReschedule}
            onCancel={handleCancel}
          />
        )}
        keyExtractor={(item) => item.id}
        contentContainerClassName="p-4"
        ListEmptyComponent={
          <Text className="my-4 text-xl font-pbold text-primaryLight">
            No Appointments
          </Text>
        }
      />
    </SafeAreaView>
  );
}
